<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="campaign">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.campaign.detail.title')">Campaign</span> {{campaign.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.langKey')">Lang Key</span>
                    </dt>
                    <dd>
                        <span v-text="$t('influSuccessApp.LangKey.' + campaign.langKey)">{{campaign.langKey}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{campaign.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{campaign.description}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.status')">Status</span>
                    </dt>
                    <dd>
                        <span v-text="$t('influSuccessApp.CampaignStatus.' + campaign.status)">{{campaign.status}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.minFollowers')">Min Followers</span>
                    </dt>
                    <dd>
                        <span>{{campaign.minFollowers}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.maxFollowers')">Max Followers</span>
                    </dt>
                    <dd>
                        <span>{{campaign.maxFollowers}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.targetCountries')">Target Countries</span>
                    </dt>
                    <dd>
                        <span>{{campaign.targetCountries}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.campaign.user')">User</span>
                    </dt>
                    <dd>
                        {{campaign.user ? campaign.user.id : ''}}
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="campaign.id" :to="{name: 'CampaignEdit', params: {campaignId: campaign.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./campaign-details.component.ts">
</script>
