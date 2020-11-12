<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="socialNetwork">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.socialNetwork.detail.title')">SocialNetwork</span> {{socialNetwork.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.socialNetwork.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{socialNetwork.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.socialNetwork.campaign')">Campaign</span>
                    </dt>
                    <dd>
                        <div v-if="socialNetwork.campaign">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: socialNetwork.campaign.id}}">{{socialNetwork.campaign.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="socialNetwork.id" :to="{name: 'SocialNetworkEdit', params: {socialNetworkId: socialNetwork.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./social-network-details.component.ts">
</script>
