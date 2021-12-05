<template>
  <q-layout view="hHh lpR fff">
    <q-header elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title shrink>
          <q-avatar>
            <img src="./assets/3.png">
          </q-avatar>
          Knowledge Based Systems - Plagiarism
        </q-toolbar-title>
        <q-space/>
        <q-avatar square style="width: 130px; height: 60px; float: right; margin: 5px">
          <img src="./assets/1.png">
        </q-avatar>
      </q-toolbar>
      <q-tabs>
        <q-route-tab to="/home" label="Home"/>
        <q-route-tab to="/administration" label="Administration"/>
        <q-route-tab to="/upload-files" label="Upload files"/>
      </q-tabs>
    </q-header>

    <q-page-container>
      <suspense>
        <router-view/>
      </suspense>
    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <q-toolbar-title shrink>
          <q-avatar square style="width: 130px; height: 60px;">
            <img src="./assets/7.jpg">
          </q-avatar>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script lang="ts" setup>
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.centered-div {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  min-height: 90vh;
}
</style>
