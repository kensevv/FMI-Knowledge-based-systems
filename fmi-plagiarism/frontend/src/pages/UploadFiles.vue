<template>
  <q-page padding>
    <div class="row">
      <div class="col-5"></div>
      <div class="col">
        <q-uploader
            url="http://localhost:8080/api/file-upload"
            label="Upload files"
            multiple
            field-name="file"
            :headers="(file) => getHeader(file)"
            square
            bordered
            accept=".txt"
            @rejected="onRejected"
        />
      </div>
    </div>
  </q-page>
</template>

<script lang="ts" setup>

import {useQuasar} from "quasar";

const quasar = useQuasar()
const onRejected = (rejectedFiles) => {
  quasar.notify({
    type: 'negative',
    message: `Txt files expected. ${rejectedFiles[0]?.file?.name} has wrong file format.`
  })
}
const getHeader = (file) => {
  console.log(file);
  return [{name: 'File-Name', value: file[0].name}]
}
</script>

<style scoped>

</style>