<template>
    <div class="container-fluid  ">
        <div class ="row">
        <div id="campaigns" class=" col-6 ">
            <h1> My campaigns</h1>
            <div class="dataGridBtnActions d-flex flex-row mb-1 responsive ">
                <b-button class="mr-1" v-b-modal.newCampaign variant="primary"> Add new campaign</b-button>
                <b-button :disabled="!rowIsSelected" v-b-modal.editCampaign class="mr-1" variant="warning"> Consult/Edit campaign</b-button>
                <b-button :disabled="!rowIsSelected" v-b-modal.removeCampaign class=" mr-1" variant="danger"> Delete campaign</b-button>
            </div>
            <div class="alert alert-warning" v-if="!isFetching && campaigns && campaigns.length === 0">
                <span v-text="$t('influSuccessApp.campaigns.home.notFound')">No campaigns found</span>
            </div>
            <div v-if="campaigns && campaigns.length > 0">
                <b-table hover :items="campaigns"
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
        <div id="discussionThreads" class="col-6">
            <h2> My discussion threads </h2>
            <discussion-threads></discussion-threads>
        </div>
        </div>
        <!-- modals -->
        <b-modal id="newCampaign" :hide-footer="true" size="xl" title="Add campaign Modal">
            <campaign-update :modalTitle="'addLabel'" :idCampaignModal="'newCampaign'" @campaignSaved="this.retrieveCampaigns"></campaign-update>
        </b-modal>
        <b-modal id="editCampaign" :hide-footer="true" size="xl" title="Edit selected campaign Modal">
            <campaign-update :modalTitle="'editLabel'" :campaign="selectedCampaign" :idCampaignModal="'editCampaign'" @campaignSaved="this.retrieveCampaigns"></campaign-update>
        </b-modal>

        <b-modal ref="removeEntity" id="removeCampaign">
            <span slot="modal-title"><span id="influSuccessApp.campaign.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-campaign-heading" v-text="$t('influSuccessApp.campaign.delete.question', {'id': selectedCampaign.title})">Are you sure you want to delete this
                    Campaign?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')"
                        v-on:click="closeDialogue('removeCampaign')">Cancel
                </button>
                <button type="button" class="btn btn-danger" id="jhi-confirm-delete-campaign" v-text="$t('entity.action.delete')" v-on:click="removeCampaign()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./advDashboard.component.ts">
</script>
<style scoped>
</style>



