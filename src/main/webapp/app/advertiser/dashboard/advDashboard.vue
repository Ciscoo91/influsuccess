<template>
<div class="container-fluid d-flex flex-row align-items-stretch ">
  <div id="campaigns">
      <h1> My campaigns</h1>
      <div class = "dataGridBtnActions d-flex flex-row mb-1 responsive ">
         <b-button class="mr-1" v-b-modal.newCampaign variant="primary"> Add new campaign</b-button>
          <b-button :disabled="!rowIsSelected" v-b-modal.editCampaign  class="mr-1" variant="warning"> Edit campaign</b-button>
          <b-button :disabled="!rowIsSelected" v-b-modal.removeCampaign class=" mr-1" variant="danger"> Delete campaign</b-button>
          <b-button :disabled="!rowIsSelected" v-b-modal.consultCampaign class="mr-1"  variant="info"> Consult</b-button>
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
    <b-modal id="newCampaign" size="xl" title="Add campaign Modal">Form for adding new campaign</b-modal>
    <b-modal id="editCampaign" size="xl" title="Edit selected campaign Modal">Form for adding editing selected campaign</b-modal>
    <b-modal id="consultCampaign" size="xl" title="Consult selected camapign Modal">Form for consulting selected campaign</b-modal>

    <b-modal ref="removeEntity" id="removeCampaign" >
        <span slot="modal-title"><span id="influSuccessApp.campaign.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
        <div class="modal-body">
            <p id="jhi-delete-campaign-heading" v-text="$t('influSuccessApp.campaign.delete.question', {'id': removeId})">Are you sure you want to delete this Campaign?</p>
        </div>
        <div slot="modal-footer">
            <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
            <button type="button" class="btn btn-primary" id="jhi-confirm-delete-campaign" v-text="$t('entity.action.delete')" v-on:click="removeCampaign()">Delete</button>
        </div>
    </b-modal>
</div>
</template>

<script lang="ts" src="./advDashboard.component.ts">
</script>
<style scoped>
</style>



