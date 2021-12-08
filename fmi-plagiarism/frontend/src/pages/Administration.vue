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
                 :filter="filter"
                 :rows="tableContent"
                 :columns="columns">
          <template v-slot:top-left>
            <div class="row">
              <div class="q-table__title">Data Files</div>
              <div style="margin-left: 150px; border-bottom: 1px solid grey">
                Filters
                <q-checkbox v-model="verified" label="Verified" color="green"/>
                <q-checkbox v-model="toBeVerified" label="To be verified"/>
                <q-checkbox v-model="plagiarismDetected" label="Plagiarism Detected" color="red"/>
                <q-checkbox v-model="plagiarismNotDetected" label="Plagiarism Not Detected" color="green"/>
              </div>
            </div>
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
import {getAllDataFiles} from "../services/request-service";
import {ref, watch} from "vue";
import {useQuasar} from "quasar";
import FileContentDialog from "../components/FileContentDialog.vue";

const quasar = useQuasar()

const data = await getAllDataFiles()
const tableContent = ref<DataFiles[]>(data)

const filter = ref('')

const verified = ref<boolean>(true)
const toBeVerified = ref<boolean>(true)
const plagiarismDetected = ref<boolean>(true)
const plagiarismNotDetected = ref<boolean>(true)

watch([verified, toBeVerified, plagiarismDetected, plagiarismNotDetected], () => {
  tableContent.value = data
  if (verified.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.verified === 'N')
  }
  if (toBeVerified.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.verified === 'Y')
  }
  if (plagiarismDetected.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.plagiarismDetected === 'N' || dataFile.plagiarismDetected == null)
  }
  if (plagiarismNotDetected.value == false) {
    tableContent.value = tableContent.value.filter(dataFile => dataFile.plagiarismDetected === 'Y' || dataFile.plagiarismDetected == null)
  }
})

const showContent = (dataFileRecord: DataFiles) => {
  quasar.dialog({
    component: FileContentDialog,
    componentProps: {
      'dataFile': {...dataFileRecord},
    }
  })
}

const columns = [
  {name: "id", label: "UUID", field: "id", align: "left", sortable: true},
  {name: "fileName", label: "File Name", field: "fileName", align: "left", sortable: true},
  {name: "uploadDate", label: "Upload Date", field: "uploadDate", align: "left", sortable: true},
  {name: "verified", label: "Verified", field: "verified", align: "left", sortable: true},
  {name: "plagiarism", label: "Plagiarism Detected", field: "plagiarismDetected", align: "left", sortable: true},
  {name: "manage"}
]

const getRowClass = (dataFileRecord: DataFiles) => {
  if (dataFileRecord.plagiarismDetected === 'Y') return 'bg-red-2'
  if (dataFileRecord.verified === 'N') return 'bg-blue-2'
  return 'bg-green-2'
}
</script>

<style scoped>

</style>