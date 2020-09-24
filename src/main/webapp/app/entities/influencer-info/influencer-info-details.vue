<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="influencerInfo">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.influencerInfo.detail.title')">InfluencerInfo</span> {{influencerInfo.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.influencerInfo.user')">User</span>
                    </dt>
                    <dd>
                        {{influencerInfo.user ? influencerInfo.user.id : ''}}
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="influencerInfo.id" :to="{name: 'InfluencerInfoEdit', params: {influencerInfoId: influencerInfo.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./influencer-info-details.component.ts">
</script>
