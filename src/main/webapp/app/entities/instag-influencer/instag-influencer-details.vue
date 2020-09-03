<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="instagInfluencer">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.instagInfluencer.detail.title')">InstagInfluencer</span> {{instagInfluencer.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.instagInfluencer.url')">Url</span>
                    </dt>
                    <dd>
                        <span>{{instagInfluencer.url}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="instagInfluencer.id" :to="{name: 'InstagInfluencerEdit', params: {instagInfluencerId: instagInfluencer.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./instag-influencer-details.component.ts">
</script>
