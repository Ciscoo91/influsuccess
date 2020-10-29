<template>
  <div class="container mt-5">
    <div class="alert alert-warning" v-if="!isFetching && discussions && discussions.length === 0">
      <span v-text="$t('influSuccessApp.discussion.home.notFound')">No discussions found</span>
    </div>

    <div v-if="discussions && discussions.length > 0">
      <b-list-group v-for="discussion of discussions" :key="discussion.discussionId" class="w-100">
        <b-list-group-item
          v-on:click="openChat(discussion)"
          :button="true"
          active
          class="text-secondary bg-secondary border-0 shadow-sm my-2 w-100"
        >
          <div class="d-flex flex-column justify-content-between w-100">
            <h5 class="mb-1 d-flex justify-content-between align-items-center">
              <span class="text-danger">{{ discussion.campaignTitle.charAt(0).toUpperCase() + discussion.campaignTitle.slice(1) }}: </span>
              <small class="ml-5 text-dark">3 days ago</small>
            </h5>
            <div>
              <font-awesome-icon icon="user" class="text-dark" />
              <span class="text-primary" v-for="participant in discussion.loginParticipants" :key="participant"> {{ participant }}</span>
            </div>
          </div>
          <div class="d-flex w-100 justify-content-between w-100">
            <p class="mb-1 text-dark">{{ discussion.lastMessage }}</p>
            <small
              v-if="discussion.countNewMessages && discussion.countNewMessages > 0"
              class="badge badge-danger text-white d-flex align-items-center"
              >{{ discussion.countNewMessages }}</small
            >
          </div>
        </b-list-group-item>
      </b-list-group>
      <b-modal id="chatModal" size="xl" :hide-header="true" :ok-only="true" ok-title="Close" @hide="closeChat" @close="closeChat">
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
          :submitIconSize="submitIconSize"
        />
      </b-modal>
    </div>
  </div>
</template>

<script lang="ts" src="./discussionThreads.component.ts"></script>
