<template>
  <q-dialog ref="dialogRef" @hide="onDialogHide">
    <q-card class="q-dialog-plugin" style="min-width: 70vh; width: auto; max-width: 140vh">
      <q-card-section>
        <div class="text-h6"> Content of "{{ dataFile.fileName }}"</div>
      </q-card-section>
      <q-separator/>
      <q-card-section style="overflow-wrap: break-word;" class="scroll">
        {{ dataFile.text }}
      </q-card-section>
      <q-separator/>
      <q-card-actions align="right">
        <q-btn flat label="Close" color="primary" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script lang="ts" setup>
import {useDialogPluginComponent} from "quasar";

const {dialogRef, onDialogHide, onDialogOK, onDialogCancel} = useDialogPluginComponent()

const props = defineProps<{
  dataFile: DataFiles,
}>();

defineEmits([...useDialogPluginComponent.emits])
</script>

<style scoped>

</style>