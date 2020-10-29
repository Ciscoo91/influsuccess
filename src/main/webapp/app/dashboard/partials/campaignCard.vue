<template>
  <div>
    <div id="campaigns" class="col-6">
      <h1>My campaigns</h1>
      <div class="dataGridBtnActions d-flex flex-row mb-1 responsive">
        <b-button class="mr-1" v-b-modal.newCampaign variant="primary"> Add new campaign</b-button>
        <b-button :disabled="!rowIsSelected" v-b-modal.editCampaign class="mr-1" variant="warning"> Consult/Edit campaign</b-button>
        <b-button :disabled="!rowIsSelected" v-b-modal.removeCampaign class="mr-1" variant="danger"> Delete campaign</b-button>
      </div>
      <div class="alert alert-warning" v-if="!isFetching && campaigns && campaigns.length === 0">
        <span v-text="$t('influSuccessApp.campaigns.home.notFound')">No campaigns found</span>
      </div>
      <div v-if="campaigns && campaigns.length > 0">
        <b-table
          hover
          :items="campaigns"
          selectable
          :fields="fields"
          :selected-variant="'primary'"
          :select-mode="'single'"
          :head-variant="'none'"
          :no-border-collapse="true"
          :outlined="true"
          @row-selected="onRowSelected"
        ></b-table>
      </div>
    </div>
  </div>
</template>

<script src="./campaignCard.component.ts"></script>