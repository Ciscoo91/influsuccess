<template>
<div class="container-fluid d-flex flex-row align-items-stretch ">
  <div id="campaigns">
      <h1> My campaigns</h1>
      <div class = "dataGridBtnActions d-flex flex-row mb-1 responsive ">
         <b-button class="mr-1"  variant="primary"> Add new campaign</b-button>
          <b-button :disabled="!rowIsSelected" class="mr-1" variant="warning"> Edit campaign</b-button>
          <b-button :disabled="!rowIsSelected" class=" mr-1" variant="danger"> Delete campaign</b-button>
          <b-button :disabled="!rowIsSelected" class="mr-1"  variant="info"> Consult</b-button>
      </div>
      <b-table hover :items="campaigns"
               selectable
               :selected-variant="'primary'"
               :select-mode="'single'"
               :head-variant="'none'"
               :no-border-collapse="true"
               :outlined="true"
               @row-selected="onRowSelected"
                ></b-table>
  </div>
</div>
</template>

<script lang="ts" src="./advDashboard.component.ts">
</script>
<style scoped>
</style>



