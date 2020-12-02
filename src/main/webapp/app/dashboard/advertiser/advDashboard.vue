<template>
  <div class="container-fluid">
    <div class="row d-flex flex-md-row flex-sm-column justify-content-between align-sm-items-center">
      <side-navbar  v-on:currentComponent="setCurrentComponent"></side-navbar>
      <keep-alive>
        <component :is="currentComponent"></component>
      </keep-alive>
    </div>
  </div>
</template>

<script lang="ts" src="./advDashboard.component.ts">
</script>
<style scoped>
</style>