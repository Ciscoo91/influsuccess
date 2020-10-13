<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('influSuccessApp.message.home.title')" id="message-heading">Messages</span>
            <router-link :to="{name: 'MessageCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-message">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('influSuccessApp.message.home.createLabel')">
                    Create a new Message
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
        <div class="alert alert-warning" v-if="!isFetching && messages && messages.length === 0">
            <span v-text="$t('influSuccessApp.message.home.notFound')">No messages found</span>
        </div>
        <div class="table-responsive" v-if="messages && messages.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('influSuccessApp.message.status')">Status</span></th>
                    <th><span v-text="$t('influSuccessApp.message.content')">Content</span></th>
                    <th><span v-text="$t('influSuccessApp.message.sender')">Sender</span></th>
                    <th><span v-text="$t('influSuccessApp.message.receiver')">Receiver</span></th>
                    <th><span v-text="$t('influSuccessApp.message.discussion')">Discussion</span></th>
                    <th><span v-text="$t('influSuccessApp.message.discussion')">Discussion</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="message in messages"
                    :key="message.id">
                    <td>
                        <router-link :to="{name: 'MessageView', params: {messageId: message.id}}">{{message.id}}</router-link>
                    </td>
                    <td v-text="$t('influSuccessApp.MessageStatus.' + message.status)">{{message.status}}</td>
                    <td>{{message.content}}</td>
                    <td>
                        {{message.sender ? message.sender.id : ''}}
                    </td>
                    <td>
                        {{message.receiver ? message.receiver.id : ''}}
                    </td>
                    <td>
                        <div v-if="message.discussion">
                            <router-link :to="{name: 'DiscussionView', params: {discussionId: message.discussion.id}}">{{message.discussion.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="message.discussion">
                            <router-link :to="{name: 'DiscussionView', params: {discussionId: message.discussion.id}}">{{message.discussion.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MessageView', params: {messageId: message.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MessageEdit', params: {messageId: message.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(message)"
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
            <span slot="modal-title"><span id="influSuccessApp.message.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-message-heading" v-text="$t('influSuccessApp.message.delete.question', {'id': removeId})">Are you sure you want to delete this Message?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-message" v-text="$t('entity.action.delete')" v-on:click="removeMessage()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./message.component.ts">
</script>
