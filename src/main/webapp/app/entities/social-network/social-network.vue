<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.socialNetwork.home.title')" id="social-network-heading">Social Networks</span>
            <router-link :to="{name: 'SocialNetworkCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-social-network">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.socialNetwork.home.createLabel')">
                    Create a new Social Network
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
        <div class="alert alert-warning" v-if="!isFetching && socialNetworks && socialNetworks.length === 0">
            <span v-text="$t('influSuccessApp.socialNetwork.home.notFound')">No socialNetworks found</span>
        </div>
        <div class="table-responsive" v-if="socialNetworks && socialNetworks.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.socialNetwork.name')">Name</span></th>
                    <th><span v-text="$t('influSuccessApp.socialNetwork.campaign')">Campaign</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="socialNetwork in socialNetworks"
                    :key="socialNetwork.id">
                    <td>
                        <router-link :to="{name: 'SocialNetworkView', params: {socialNetworkId: socialNetwork.id}}">{{socialNetwork.id}}</router-link>
                    </td>
                    <td>{{socialNetwork.name}}</td>
                    <td>
                        <div v-if="socialNetwork.campaign">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: socialNetwork.campaign.id}}">{{socialNetwork.campaign.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SocialNetworkView', params: {socialNetworkId: socialNetwork.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SocialNetworkEdit', params: {socialNetworkId: socialNetwork.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(socialNetwork)"
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
            <span slot="modal-title"><span id="influSuccessApp.socialNetwork.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-socialNetwork-heading" v-text="$t('influSuccessApp.socialNetwork.delete.question', {'id': removeId})">Are you sure you want to delete this Social Network?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-socialNetwork" v-text="$t('entity.action.delete')" v-on:click="removeSocialNetwork()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./social-network.component.ts">
</script>
