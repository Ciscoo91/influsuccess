<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.contact.home.createOrEditLabel" v-text="$t('influSuccessApp.contact.home.createOrEditLabel')">Create or edit a Contact</h2>
                <div>
                    <div class="form-group" v-if="contact.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contact.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.contact.user')" for="contact-user">User</label>
                        <select class="form-control" id="contact-user" name="user" v-model="contact.user">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contact.user && userOption.id === contact.user.id ? contact.user : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.contact.compaign')" for="contact-compaign">Compaign</label>
                        <select class="form-control" id="contact-compaign" name="compaign" v-model="contact.compaign">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contact.compaign && campaignOption.id === contact.compaign.id ? contact.compaign : campaignOption" v-for="campaignOption in campaigns" :key="campaignOption.id">{{campaignOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.contact.instagInfluencer')" for="contact-instagInfluencer">Instag Influencer</label>
                        <select class="form-control" id="contact-instagInfluencer" name="instagInfluencer" v-model="contact.instagInfluencer">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="contact.instagInfluencer && instagInfluencerOption.id === contact.instagInfluencer.id ? contact.instagInfluencer : instagInfluencerOption" v-for="instagInfluencerOption in instagInfluencers" :key="instagInfluencerOption.id">{{instagInfluencerOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contact.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contact-update.component.ts">
</script>
