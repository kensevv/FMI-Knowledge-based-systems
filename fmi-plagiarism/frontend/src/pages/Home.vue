<template>
  Home
  {{ testFetch }}
</template>

<script lang="ts" setup>
import {testFun} from "../services/request-service";

const testFetch = await testFun()
</script>

<style scoped>

</style>