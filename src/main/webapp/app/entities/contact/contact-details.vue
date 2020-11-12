<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contact">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.contact.detail.title')">Contact</span> {{contact.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.contact.user')">User</span>
                    </dt>
                    <dd>
                        {{contact.user ? contact.user.id : ''}}
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.contact.compaign')">Compaign</span>
                    </dt>
                    <dd>
                        <div v-if="contact.compaign">
                            <router-link :to="{name: 'CampaignView', params: {campaignId: contact.compaign.id}}">{{contact.compaign.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.contact.instagInfluencer')">Instag Influencer</span>
                    </dt>
                    <dd>
                        <div v-if="contact.instagInfluencer">
                            <router-link :to="{name: 'InstagInfluencerView', params: {instagInfluencerId: contact.instagInfluencer.id}}">{{contact.instagInfluencer.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="contact.id" :to="{name: 'ContactEdit', params: {contactId: contact.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contact-details.component.ts">
</script>
