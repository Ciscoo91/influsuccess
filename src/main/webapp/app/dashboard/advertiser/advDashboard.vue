<template>
  <div class="container-fluid">
    <div class="row">
      <side-navbar v-on:currentComponent="setCurrentComponent"></side-navbar>
      <keep-alive class="col-10">
        <component :is="currentComponent"></component>
      </keep-alive>
    </div>

    <!-- modals -->
    <b-modal id="newCampaign" :hide-footer="true" size="xl" title="Add campaign Modal">
      <campaign-update :modalTitle="'addLabel'" :idCampaignModal="'newCampaign'" @campaignSaved="this.retrieveCampaigns"></campaign-update>
    </b-modal>
    <b-modal id="editCampaign" :hide-footer="true" size="xl" title="Edit selected campaign Modal">
      <campaign-update
        :modalTitle="'editLabel'"
        :campaign="selectedCampaign"
        :idCampaignModal="'editCampaign'"
        @campaignSaved="this.retrieveCampaigns"
      ></campaign-update>
    </b-modal>

    <b-modal ref="removeEntity" id="removeCampaign">
      <span slot="modal-title"
        ><span id="influSuccessApp.campaign.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-campaign-heading" v-text="$t('influSuccessApp.campaign.delete.question', { id: selectedCampaign.title })">
          Are you sure you want to delete this Campaign?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialogue('removeCampaign')">
          Cancel
        </button>
        <button
          type="button"
          class="btn btn-danger"
          id="jhi-confirm-delete-campaign"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCampaign()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./advDashboard.component.ts">
</script>
<style scoped>
</style>



