<template>
  <q-page padding>
    <div class="row">
      <q-table title="Data Files" :rows="data"></q-table>
    </div>
  </q-page>
</template>

<script lang="ts" setup>
import {getAllDataFiles} from "../services/request-service";

const data = await getAllDataFiles()
</script>

<style scoped>

</style>