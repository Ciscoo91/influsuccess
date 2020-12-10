<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="socialNetworkLink">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.socialNetworkLink.detail.title')">SocialNetworkLink</span> {{socialNetworkLink.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.socialNetworkLink.link')">Link</span>
                    </dt>
                    <dd>
                        <span>{{socialNetworkLink.link}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.socialNetworkLink.socialNetwork')">Social Network</span>
                    </dt>
                    <dd>
                        <div v-if="socialNetworkLink.socialNetwork">
                            <router-link :to="{name: 'SocialNetworkView', params: {socialNetworkId: socialNetworkLink.socialNetwork.id}}">{{socialNetworkLink.socialNetwork.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.socialNetworkLink.influencer')">Influencer Info</span>
                    </dt>
                    <dd>
                        <div v-if="socialNetworkLink.influencer">
                            <router-link :to="{name: 'InfluencerInfoView', params: {influencerInfoId: socialNetworkLink.influencer.id}}">{{socialNetworkLink.influencer.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="socialNetworkLink.id" :to="{name: 'SocialNetworkLinkEdit', params: {socialNetworkLinkId: socialNetworkLink.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./social-network-link-details.component.ts">
</script>
