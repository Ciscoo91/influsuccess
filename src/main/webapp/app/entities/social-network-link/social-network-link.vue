<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.socialNetworkLink.home.title')" id="social-network-link-heading">Social Network Links</span>
            <router-link :to="{name: 'SocialNetworkLinkCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-social-network-link">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.socialNetworkLink.home.createLabel')">
                    Create a new Social Network Link
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
        <div class="alert alert-warning" v-if="!isFetching && socialNetworkLinks && socialNetworkLinks.length === 0">
            <span v-text="$t('influSuccessApp.socialNetworkLink.home.notFound')">No socialNetworkLinks found</span>
        </div>
        <div class="table-responsive" v-if="socialNetworkLinks && socialNetworkLinks.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.socialNetworkLink.link')">Link</span></th>
                    <th><span v-text="$t('influSuccessApp.socialNetworkLink.socialNetwork')">Social Network</span></th>
                    <th><span v-text="$t('influSuccessApp.socialNetworkLink.influencer')">Influencer Info</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="socialNetworkLink in socialNetworkLinks"
                    :key="socialNetworkLink.id">
                    <td>
                        <router-link :to="{name: 'SocialNetworkLinkView', params: {socialNetworkLinkId: socialNetworkLink.id}}">{{socialNetworkLink.id}}</router-link>
                    </td>
                    <td>{{socialNetworkLink.link}}</td>
                    <td>
                        <div v-if="socialNetworkLink.socialNetwork">
                            <router-link :to="{name: 'SocialNetworkView', params: {socialNetworkId: socialNetworkLink.socialNetwork.id}}">{{socialNetworkLink.socialNetwork.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="socialNetworkLink.influencer">
                            <router-link :to="{name: 'InfluencerInfoView', params: {influencerInfoId: socialNetworkLink.influencer.id}}">{{socialNetworkLink.influencer.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SocialNetworkLinkView', params: {socialNetworkLinkId: socialNetworkLink.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SocialNetworkLinkEdit', params: {socialNetworkLinkId: socialNetworkLink.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(socialNetworkLink)"
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
            <span slot="modal-title"><span id="influSuccessApp.socialNetworkLink.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-socialNetworkLink-heading" v-text="$t('influSuccessApp.socialNetworkLink.delete.question', {'id': removeId})">Are you sure you want to delete this Social Network Link?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-socialNetworkLink" v-text="$t('entity.action.delete')" v-on:click="removeSocialNetworkLink()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./social-network-link.component.ts">
</script>
