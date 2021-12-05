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
                 :rows="data"
                 :columns="columns">
          <template v-slot:top-right>
            <q-input v-model="filter" clearable debounce="300" dense placeholder="Search">
              <template v-slot:append>
                <q-icon name="search"/>
              </template>
            </q-input>
          </template>
          <template v-slot:body-cell-manage="props">
            <q-td class="text-left">
              <q-btn color="secondary" icon="info_outline" no-caps flat size="15px" round
                     @click="()=>showContent(props.row)">
                <q-tooltip style="font-size: large">
                  Click to see file content
                </q-tooltip>
              </q-btn>
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
import {ref} from "vue";
import {useQuasar} from "quasar";
import FileContentDialog from "../components/FileContentDialog.vue";

const quasar = useQuasar()

const data = await getAllDataFiles()

const filter = ref('')

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
</script>

<style scoped>

</style>