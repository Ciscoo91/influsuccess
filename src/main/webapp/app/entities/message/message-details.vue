<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="message">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.message.detail.title')">Message</span> {{message.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.message.status')">Status</span>
                    </dt>
                    <dd>
                        <span v-text="$t('influSuccessApp.MessageStatus.' + message.status)">{{message.status}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.message.content')">Content</span>
                    </dt>
                    <dd>
                        <span>{{message.content}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.message.sender')">Sender</span>
                    </dt>
                    <dd>
                        {{message.sender ? message.sender.id : ''}}
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.message.receiver')">Receiver</span>
                    </dt>
                    <dd>
                        {{message.receiver ? message.receiver.id : ''}}
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.message.discussion')">Discussion</span>
                    </dt>
                    <dd>
                        <div v-if="message.discussion">
                            <router-link :to="{name: 'DiscussionView', params: {discussionId: message.discussion.id}}">{{message.discussion.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.message.discussion')">Discussion</span>
                    </dt>
                    <dd>
                        <div v-if="message.discussion">
                            <router-link :to="{name: 'DiscussionView', params: {discussionId: message.discussion.id}}">{{message.discussion.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="message.id" :to="{name: 'MessageEdit', params: {messageId: message.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./message-details.component.ts">
</script>
