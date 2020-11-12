<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.campaign.home.title')" id="campaign-heading">Campaigns</span>
            <router-link :to="{name: 'CampaignCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-campaign">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.campaign.home.createLabel')">
                    Create a new Campaign
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && campaigns && campaigns.length === 0">
            <span v-text="$t('influSuccessApp.campaign.home.notFound')">No campaigns found</span>
        </div>
        <div class="table-responsive" v-if="campaigns && campaigns.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.campaign.title')">Title</span></th>
                    <th><span v-text="$t('influSuccessApp.campaign.user')">User</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="campaign in campaigns"
                    :key="campaign.id">
                    <td>
                        <router-link :to="{name: 'CampaignView', params: {campaignId: campaign.id}}">{{campaign.id}}</router-link>
                    </td>
                    <td>{{campaign.title}}</td>
                    <td>
                        {{campaign.user ? campaign.user.id : ''}}
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: campaign.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CampaignEdit', params: {campaignId: campaign.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(campaign)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
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

<script lang="ts" src="./campaign.component.ts">
</script>
