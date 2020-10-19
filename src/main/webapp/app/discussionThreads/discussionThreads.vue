<template>
    <div>
        <div class="alert alert-warning" v-if="!isFetching && discussions && discussions.length === 0">
            <span v-text="$t('influSuccessApp.discussion.home.notFound')">No discussions found</span>
        </div>

        <div v-if="discussions && discussions.length > 0">
            <b-list-group v-for="discussion of discussions" :key="discussion.discussionId" >
                <b-list-group-item  v-on:click="openChat(discussion)"  :button="true" active class="flex-column align-items-start  text-secondary bg-info">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">
                            <span class="text-white">{{discussion.campaignTitle}}</span>:
                            <span class="text-warning"> {{discussion.loginParticipants.toString()}}</span>
                        </h5>
                        <small>3 days ago</small>
                    </div>
                    <div class="d-flex w-100 justify-content-between">
                    <p class="mb-1" >
                        {{discussion.lastMessage}}
                    </p>
                        <small v-if="discussion.countNewMessages && discussion.countNewMessages > 0" class="text-danger">{{discussion.countNewMessages}}</small>
                    </div>
                </b-list-group-item>
            </b-list-group>
            <b-modal id="chatModal" size="xl" :hide-header="true" :ok-only="true"  ok-title="Close" @hide="closeChat" @close="closeChat">
            <Chat
                :participants="participants"
                :myself="myself"
                :messages="messages"
                @onMessageSubmit="onMessageSubmit"
                :chatTitle="chatTitle"
                :placeholder="placeholder"
                :colors="colors"
                :timestamp-config="timestampConfig"
                :borderStyle="borderStyle"
                :hideCloseButton="hideCloseButton"
                :closeButtonIconSize="closeButtonIconSize"
                :submitIconSize="submitIconSize"/>
            </b-modal>
        </div>
    </div>
</template>

<script lang="ts" src="./discussionThreads.component.ts"></script>
