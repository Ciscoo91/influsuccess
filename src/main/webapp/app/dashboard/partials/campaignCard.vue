<template>
  <div class="col-9 mx-auto px-0 bg-transparent">
    <div id="campaigns" class="d-flex flex-column">
      <div class="dataGridBtnActions d-flex flex-row justify-content-between align-self-end my-3 responsive w-100">
        <b-button class="mr-1" v-b-modal.newCampaign variant="primary"> Add new campaign</b-button>
      </div>
      <div class="alert alert-warning" v-if="!isFetching && campaigns && campaigns.length === 0">
        <span v-text="$t('influSuccessApp.campaigns.home.notFound')">No campaigns found</span>
      </div>
      <div v-if="campaigns && campaigns.length > 0" class="row">
        <div class="cardGroup row w-100" v-if="campaigns && campaigns.length > 0">
          <div class="mb-3 selectableCard col-md-6" v-for="campaign of campaigns" :key="campaign.id">
            <b-card border-variant="primary" header-bg-variant="primary" header-text-variant="white">
              <template v-slot:header>
                <div>
                  <div class="d-flex w-100 justify-content-between">
                    <h3>{{ campaign.title }}</h3>
                    <div class="dataGridBtnActions d-flex flex-row mb-1 responsive">
                      <b-button @click="initDiscussionThread(campaign)" class="mr-1" variant="warning"> Contact the client</b-button>
                    </div>
                  </div>

                  <div class="d-flex w-100">
                    <b-badge class="mr-3" :variant="`${campaign.status === 'OPENED' ? 'success' : 'danger'}`">
                      {{ campaign.status }}
                    </b-badge>
                    <span v-text="$t('influSuccessApp.influDashboard.minFollowers', { param: campaign.minFollowers })">
                      At least {{ campaign.minFollowers }} followers
                    </span>
                  </div>
                </div>
              </template>
              <b-card-text>
                <p>{{ campaign.description }}</p>
                <p>
                  <span
                    class="font-weight-bold"
                    v-text="$t('influSuccessApp.influDashboard.targetCountries', { param: campaign.targetCountries })"
                    >Country of audience:{{ campaign.targetCountries }}</span
                  >
                </p>
              </b-card-text>
              <template v-slot:footer>
                <div class="d-flex w-100 justify-content-between align-items-center">
                  <div class="social d-flex">
                    <div v-for="socialNetwork of campaign.socialNetworks" :key="socialNetwork.id">
                      <b-button :disabled="true" :pill="true" class="mr-3">{{ socialNetwork.name }}</b-button>
                    </div>
                  </div>
                  <div>
                    <font-awesome-icon icon="edit" class="text-warning hover mr-2" v-b-modal.editCampaign />
                    <font-awesome-icon icon="trash" class="text-danger hover" v-b-modal.removeCampaign />
                  </div>
                </div>
              </template>
            </b-card>
          </div>
        </div>
      </div>
      <!-- modals -->
      <b-modal id="newCampaign" :hide-footer="true" size="xl" title="Add campaign Modal">
        <campaign-update
          :modalTitle="'addLabel'"
          :idCampaignModal="'newCampaign'"
          @campaignSaved="this.retrieveOpenedCampaigns"
        ></campaign-update>
      </b-modal>
      <b-modal id="editCampaign" :hide-footer="true" size="xl" title="Edit selected campaign Modal">
        <campaign-update
          :modalTitle="'editLabel'"
          :campaign="selectedCampaign"
          :idCampaignModal="'editCampaign'"
          @campaignSaved="this.retrieveOpenedCampaigns"
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
  </div>
</template>

<script src="./campaignCard.component.ts"></script>


<style scoped>
.card:hover {
  background: #eee;
}

.cardSelected {
  background: #0d47a1;
}

.hover:hover {
  cursor: pointer;
}

.hover:visited,
.hover:active {
  outline: none;
}
</style>