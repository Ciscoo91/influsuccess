<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.contact.home.title')" id="contact-heading">Contacts</span>
            <router-link :to="{name: 'ContactCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-contact">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.contact.home.createLabel')">
                    Create a new Contact
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
        <div class="alert alert-warning" v-if="!isFetching && contacts && contacts.length === 0">
            <span v-text="$t('influSuccessApp.contact.home.notFound')">No contacts found</span>
        </div>
        <div class="table-responsive" v-if="contacts && contacts.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.contact.user')">User</span></th>
                    <th><span v-text="$t('influSuccessApp.contact.compaign')">Compaign</span></th>
                    <th><span v-text="$t('influSuccessApp.contact.instagInfluencer')">Instag Influencer</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contact in contacts"
                    :key="contact.id">
                    <td>
                        <router-link :to="{name: 'ContactView', params: {contactId: contact.id}}">{{contact.id}}</router-link>
                    </td>
                    <td>
                        {{contact.user ? contact.user.id : ''}}
                    </td>
                    <td>
                        <div v-if="contact.compaign">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: contact.compaign.id}}">{{contact.compaign.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="contact.instagInfluencer">
                            <router-link :to="{name: 'InstagInfluencerView', params: {instagInfluencerId: contact.instagInfluencer.id}}">{{contact.instagInfluencer.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContactView', params: {contactId: contact.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ContactEdit', params: {contactId: contact.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contact)"
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
            <span slot="modal-title"><span id="influSuccessApp.contact.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contact-heading" v-text="$t('influSuccessApp.contact.delete.question', {'id': removeId})">Are you sure you want to delete this Contact?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contact" v-text="$t('entity.action.delete')" v-on:click="removeContact()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./contact.component.ts">
</script>
