<template>
  <div class="container-fluid">
    <div class="row mt-5">
      <div id="campaigns" class="col-6">
        <h2 class="mb-5">My campaigns</h2>

        <div class="alert alert-warning" v-if="!isFetching && campaigns && campaigns.length === 0">
          <span v-text="$t('influSuccessApp.campaigns.home.notFound')">No campaigns found</span>
        </div>
        <div class="cardGroup d-flex flex-column mt-4" v-if="campaigns && campaigns.length > 0">
          <div class="mb-3" v-for="campaign of campaigns" :key="campaign.id">
            <b-card border-variant="primary" header-bg-variant="primary" header-text-variant="white">
              <template v-slot:header>
                <div>
                  <div class="d-flex w-100 justify-content-between">
                    <h3>{{ campaign.title }}</h3>
                    <div class="dataGridBtnActions d-flex flex-row mb-1 responsive">
                      <b-button @click="initDiscussionThread(campaign)" class="mr-1" variant="warning"> Contact the client</b-button>
                    </div>
                  </div>

                  <div class="d-flex w-100">
                    <b-badge class="mr-3" :variant="`${campaign.status === 'OPENED' ? 'success' : 'danger'}`">
                      {{ campaign.status }}
                    </b-badge>
                    <span v-text="$t('influSuccessApp.influDashboard.minFollowers', { param: campaign.minFollowers })">
                      At least {{ campaign.minFollowers }} followers
                    </span>
                  </div>
                </div>
              </template>
              <b-card-text>
                <p>{{ campaign.description }}</p>
                <p></p>
                <p>
                  <span
                    class="font-weight-bold"
                    v-text="$t('influSuccessApp.influDashboard.targetCountries', { param: campaign.targetCountries })"
                    >Country of audience:{{ campaign.targetCountries }}</span
                  >
                </p>
              </b-card-text>
              <template v-slot:footer>
                <div class="d-flex w-100">
                  <div v-for="socialNetwork of campaign.socialNetworks" :key="socialNetwork.id">
                    <b-button :disabled="true" :pill="true" class="mr-3">{{ socialNetwork.name }}</b-button>
                  </div>
                </div>
              </template>
            </b-card>
          </div>
        </div>

        <b-modal id="chatModalInflu" size="xl" :hide-header="true" :ok-only="true" ok-title="Close" @hide="closeChat" @close="closeChat">
          <Chat
            :participants="participants"
            :myself="myself"
            :messages="messages"
            @onMessageSubmit="onMessageSubmit"
            onCloseChat
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
      <div id="discussionThreads" class="col-6">
        <h2>My discussion threads</h2>
        <discussion-threads :key="discussionThreadKeyRender"></discussion-threads>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./influencerDashboard.component.ts">
</script>
<style scoped>
.cardGroup {
  height: 85vh;
  overflow-y: scroll;
}
</style>



