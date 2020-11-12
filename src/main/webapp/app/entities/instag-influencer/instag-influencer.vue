<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.instagInfluencer.home.title')" id="instag-influencer-heading">Instag Influencers</span>
            <router-link :to="{name: 'InstagInfluencerCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-instag-influencer">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.instagInfluencer.home.createLabel')">
                    Create a new Instag Influencer
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
        <div class="alert alert-warning" v-if="!isFetching && instagInfluencers && instagInfluencers.length === 0">
            <span v-text="$t('influSuccessApp.instagInfluencer.home.notFound')">No instagInfluencers found</span>
        </div>
        <div class="table-responsive" v-if="instagInfluencers && instagInfluencers.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.instagInfluencer.url')">Url</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="instagInfluencer in instagInfluencers"
                    :key="instagInfluencer.id">
                    <td>
                        <router-link :to="{name: 'InstagInfluencerView', params: {instagInfluencerId: instagInfluencer.id}}">{{instagInfluencer.id}}</router-link>
                    </td>
                    <td>{{instagInfluencer.url}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'InstagInfluencerView', params: {instagInfluencerId: instagInfluencer.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'InstagInfluencerEdit', params: {instagInfluencerId: instagInfluencer.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(instagInfluencer)"
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
            <span slot="modal-title"><span id="influSuccessApp.instagInfluencer.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-instagInfluencer-heading" v-text="$t('influSuccessApp.instagInfluencer.delete.question', {'id': removeId})">Are you sure you want to delete this Instag Influencer?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-instagInfluencer" v-text="$t('entity.action.delete')" v-on:click="removeInstagInfluencer()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./instag-influencer.component.ts">
</script>
