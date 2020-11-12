<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.userExtra.home.title')" id="user-extra-heading">User Extras</span>
            <router-link :to="{name: 'UserExtraCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-user-extra">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.userExtra.home.createLabel')">
                    Create a new User Extra
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
        <div class="alert alert-warning" v-if="!isFetching && userExtras && userExtras.length === 0">
            <span v-text="$t('influSuccessApp.userExtra.home.notFound')">No userExtras found</span>
        </div>
        <div class="table-responsive" v-if="userExtras && userExtras.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.userExtra.country')">Country</span></th>
                    <th><span v-text="$t('influSuccessApp.userExtra.birthday')">Birthday</span></th>
                    <th><span v-text="$t('influSuccessApp.userExtra.phone')">Phone</span></th>
                    <th><span v-text="$t('influSuccessApp.userExtra.user')">User</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="userExtra in userExtras"
                    :key="userExtra.id">
                    <td>
                        <router-link :to="{name: 'UserExtraView', params: {userExtraId: userExtra.id}}">{{userExtra.id}}</router-link>
                    </td>
                    <td>{{userExtra.country}}</td>
                    <td>{{userExtra.birthday}}</td>
                    <td>{{userExtra.phone}}</td>
                    <td>
                        {{userExtra.user ? userExtra.user.id : ''}}
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UserExtraView', params: {userExtraId: userExtra.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'UserExtraEdit', params: {userExtraId: userExtra.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(userExtra)"
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
            <span slot="modal-title"><span id="influSuccessApp.userExtra.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-userExtra-heading" v-text="$t('influSuccessApp.userExtra.delete.question', {'id': removeId})">Are you sure you want to delete this User Extra?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-userExtra" v-text="$t('entity.action.delete')" v-on:click="removeUserExtra()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./user-extra.component.ts">
</script>
