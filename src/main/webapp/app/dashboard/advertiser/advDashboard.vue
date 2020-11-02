<template>
  <div class="container-fluid">
    <div class="row">
      <side-navbar v-on:currentComponent="setCurrentComponent"></side-navbar>
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