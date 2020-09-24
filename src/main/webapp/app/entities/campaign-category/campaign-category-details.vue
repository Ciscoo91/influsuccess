<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="campaignCategory">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.campaignCategory.detail.title')">CampaignCategory</span> {{campaignCategory.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.campaignCategory.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{campaignCategory.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaignCategory.influencerInfo')">Influencer Info</span>
                    </dt>
                    <dd>
                        <div v-if="campaignCategory.influencerInfo">
                            <router-link :to="{name: 'InfluencerInfoView', params: {influencerInfoId: campaignCategory.influencerInfo.id}}">{{campaignCategory.influencerInfo.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaignCategory.campaign')">Campaign</span>
                    </dt>
                    <dd>
                        <div v-if="campaignCategory.campaign">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: campaignCategory.campaign.id}}">{{campaignCategory.campaign.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="campaignCategory.id" :to="{name: 'CampaignCategoryEdit', params: {campaignCategoryId: campaignCategory.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./campaign-category-details.component.ts">
</script>
