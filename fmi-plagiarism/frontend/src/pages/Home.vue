<template>
  {{ test }}
</template>

<script lang="ts" setup>
import {testFun} from "../services/request-service";

const test = await testFun()
console.log(test);
</script>

<style scoped>

</style>