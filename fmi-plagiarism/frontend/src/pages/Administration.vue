<template>
  <q-page padding>
    <div class="row">
      <div class="col-2"></div>
      <div class="col">
        <q-table title="Data Files"
                 hide-pagination
                 virtual-scroll
                 :pagination="{rowsPerPage: 0}"
                 no-data-label="I didn't find anything for you"
                 bordered
                 selection="multiple"
                 v-model:selected="selected"
                 :filter="filter"
                 :rows="tableContent"
                 :columns="columns">
          <template v-slot:body-cell-verified="props">
            <td :class="getRowClass(props.row)">
              <q-checkbox disable :model-value="props.value"/>
            </td>
          </template>
          <template v-slot:top-left>
            <div class="row">
              <div class="q-table__title">Data Files</div>
              <div style="margin-left: 150px; border-bottom: 1px solid grey">
                Filters
                <q-checkbox v-model="verified" label="Verified" color="green"/>
                <q-checkbox v-model="toBeVerified" label="To be verified"/>
                <q-checkbox v-model="plagiarismAbove50" label="Plagiarism >= 50%" color="red"/>
                <q-checkbox v-model="plagiarismUnder50" label="Plagiarism < 50%" color="green"/>
              </div>
            </div>
          </template>
          <template v-slot:bottom>
            <q-btn :disable="selected.length <= 0"
                   flat
                   color="primary"
                   icon="plagiarism"
                   label="check selected"
                   @click="checkSelected"/>
          </template>
          <template v-slot:top-right>
            <q-input v-model="filter" clearable debounce="300" dense placeholder="Search">
              <template v-slot:append>
                <q-icon name="search"/>
              </template>
            </q-input>
          </template>
          <template v-slot:body-cell-manage="props">
            <q-td :class="getRowClass(props.row)">
              <q-btn color="secondary" icon="info_outline" no-caps flat size="15px" round
                     @click="()=>showContent(props.row)">
                <q-tooltip style="font-size: large">
                  Click to see file content
                </q-tooltip>
              </q-btn>
            </q-td>
          </template>
          <template v-slot:body-cell="props">
            <q-td
                :props="props"
                :class="getRowClass(props.row)"
            >
              {{ props.value }}
            </q-td>
          </template>
        </q-table>
      </div>
      <div class="col-2"></div>
    </div>
  </q-page>
</template>

<script lang="ts" setup>
import {checkSelectedDataFiles, getAllDataFiles} from "../services/request-service";
import {ref, watch} from "vue";
import {QSpinnerGears, useQuasar} from "quasar";
import FileContentDialog from "../components/FileContentDialog.vue";

const quasar = useQuasar()

const data = ref(await getAllDataFiles())
const tableContent = ref<DataFiles[]>(data.value)

const filter = ref('')

const selected = ref([])
const checkSelected = async () => {
  const notify = quasar.notify({
    spinner: QSpinnerGears,
    group: false,
    message: 'Running plagiarism detection on selected files!...',
  })
  await checkSelectedDataFiles(selected.value);
  notify({
    message: "Plagiarism Detection ran successfully!",
    type: "positive",
  })
  data.value = await getAllDataFiles()
  tableContent.value = data.value
  selected.value = []
}

const showContent = (dataFileRecord: DataFiles) => {
  quasar.dialog({
    component: FileContentDialog,
    componentProps: {
      'dataFile': {...dataFileRecord},
    }
  })
}

const verified = ref<boolean>(true)
const toBeVerified = ref<boolean>(true)
const plagiarismAbove50 = ref<boolean>(true)
const plagiarismUnder50 = ref<boolean>(true)

watch([verified, toBeVerified, plagiarismAbove50, plagiarismUnder50], () => {
  tableContent.value = data.value
  if (verified.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => !dataFile.verified)
  }
  if (toBeVerified.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.verified)
  }
  if (plagiarismAbove50.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.plagiarismRate < 50 || !dataFile.plagiarismRate)
  }
  if (plagiarismUnder50.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.plagiarismRate >= 50 || !dataFile.plagiarismRate)
  }
})


const columns = [
  {name: "id", label: "UUID", field: "id", align: "left", sortable: true},
  {name: "fileName", label: "File Name", field: "fileName", align: "left", sortable: true},
  {name: "uploadDate", label: "Upload Date", field: "uploadDate", align: "left", sortable: true},
  {name: "verified", label: "Verified", field: "verified", align: "left", sortable: true},
  {
    name: "plagiarism",
    label: "Plagiarism Rate",
    field: "plagiarismRate",
    align: "left",
    sortable: true,
    format: (rate) => `${rate ?? 0}%`
  },
  {name: "manage"}
]

const getRowClass = (dataFileRecord: DataFiles) => {
  if (dataFileRecord.plagiarismRate >= 50) return 'bg-red-2'
  if (!dataFileRecord.verified) return 'bg-blue-2'
  return 'bg-green-2'
}
</script>

<style scoped>

</style>