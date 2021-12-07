<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title shrink>
          <q-avatar style="width: 70px; height: 70px;">
            <img  src="./assets/3.png">
          </q-avatar>
          Plagiarism System
        </q-toolbar-title>
        <q-space/>
        <q-avatar square style="width: 100px; height: 60px; float: right;">
          <img src="./assets/1.png">
        </q-avatar>
      </q-toolbar>
      <q-tabs inline-label>
        <q-route-tab icon="home" to="/home" label="Home"/>
        <q-route-tab icon="tune" to="/administration" label="Administration"/>
        <q-route-tab icon="file_upload" to="/upload-files" label="Upload files"/>
      </q-tabs>
    </q-header>

    <q-page-container>
      <suspense>
        <router-view/>
      </suspense>
    </q-page-container>

    <q-footer elevated class="bg-grey-7 text-white">
      <q-toolbar>
        <q-toolbar-title shrink>
          <q-avatar square style="width: 140px; height: 70px;">
            <img src="./assets/7.jpg">
          </q-avatar>
          Knowledge Based Systems Project
        </q-toolbar-title>
        <q-space/>
        Kenan, Veselin, Lachezar.
        <br>
        @All rights reserved 2021.
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
