<template>
  <q-page padding>
    <div class="row">
      <div class="col">
        <q-table title="Data Files" :rows="data"></q-table>
      </div>
    </div>
  </q-page>
</template>

<script lang="ts" setup>
import {getAllDataFiles} from "../services/request-service";

const data = await getAllDataFiles()
</script>

<style scoped>

</style>