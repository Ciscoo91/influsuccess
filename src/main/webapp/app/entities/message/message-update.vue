<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.message.home.createOrEditLabel" v-text="$t('influSuccessApp.message.home.createOrEditLabel')">Create or edit a Message</h2>
                <div>
                    <div class="form-group" v-if="message.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="message.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.status')" for="message-status">Status</label>
                        <select class="form-control" name="status" :class="{'valid': !$v.message.status.$invalid, 'invalid': $v.message.status.$invalid }" v-model="$v.message.status.$model" id="message-status"  required>
                            <option value="SENT" v-bind:label="$t('influSuccessApp.MessageStatus.SENT')">SENT</option>
                            <option value="DISTRIBUTED" v-bind:label="$t('influSuccessApp.MessageStatus.DISTRIBUTED')">DISTRIBUTED</option>
                            <option value="OPENED" v-bind:label="$t('influSuccessApp.MessageStatus.OPENED')">OPENED</option>
                        </select>
                        <div v-if="$v.message.status.$anyDirty && $v.message.status.$invalid">
                            <small class="form-text text-danger" v-if="!$v.message.status.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.content')" for="message-content">Content</label>
                        <input type="text" class="form-control" name="content" id="message-content"
                            :class="{'valid': !$v.message.content.$invalid, 'invalid': $v.message.content.$invalid }" v-model="$v.message.content.$model"  required/>
                        <div v-if="$v.message.content.$anyDirty && $v.message.content.$invalid">
                            <small class="form-text text-danger" v-if="!$v.message.content.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.sender')" for="message-sender">Sender</label>
                        <select class="form-control" id="message-sender" name="sender" v-model="message.sender">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="message.sender && userOption.id === message.sender.id ? message.sender : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.receiver')" for="message-receiver">Receiver</label>
                        <select class="form-control" id="message-receiver" name="receiver" v-model="message.receiver">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="message.receiver && userOption.id === message.receiver.id ? message.receiver : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.discussion')" for="message-discussion">Discussion</label>
                        <select class="form-control" id="message-discussion" name="discussion" v-model="message.discussion">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="message.discussion && discussionOption.id === message.discussion.id ? message.discussion : discussionOption" v-for="discussionOption in discussions" :key="discussionOption.id">{{discussionOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.message.discussion')" for="message-discussion">Discussion</label>
                        <select class="form-control" id="message-discussion" name="discussion" v-model="message.discussion">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="message.discussion && discussionOption.id === message.discussion.id ? message.discussion : discussionOption" v-for="discussionOption in discussions" :key="discussionOption.id">{{discussionOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.message.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./message-update.component.ts">
</script>
