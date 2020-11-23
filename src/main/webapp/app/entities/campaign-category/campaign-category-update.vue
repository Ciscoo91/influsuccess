<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.campaignCategory.home.createOrEditLabel" v-text="$t('influSuccessApp.campaignCategory.home.createOrEditLabel')">Create or edit a CampaignCategory</h2>
                <div>
                    <div class="form-group" v-if="campaignCategory.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="campaignCategory.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaignCategory.name')" for="campaign-category-name">Name</label>
                        <input type="text" class="form-control" name="name" id="campaign-category-name"
                            :class="{'valid': !$v.campaignCategory.name.$invalid, 'invalid': $v.campaignCategory.name.$invalid }" v-model="$v.campaignCategory.name.$model"  required/>
                        <div v-if="$v.campaignCategory.name.$anyDirty && $v.campaignCategory.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaignCategory.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaignCategory.influencer')" for="campaign-category-influencer">Influencer Info</label>
                        <select class="form-control" id="campaign-category-influencer" name="influencer" v-model="campaignCategory.influencer">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="campaignCategory.influencer && influencer.id === campaignCategory.influencer.id ? campaignCategory.influencer : influencer" v-for="influencerOption in influencer" :key="influencerOption.id">{{'influencerInfoOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaignCategory.campaign')" for="campaign-category-campaign">Campaign</label>
                        <select class="form-control" id="campaign-category-campaign" name="campaign" v-model="campaignCategory.campaign">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="campaignCategory.campaign && campaignOption.id === campaignCategory.campaign.id ? campaignCategory.campaign : campaignOption" v-for="campaignOption in campaigns" :key="campaignOption.id">{{campaignOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.campaignCategory.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./campaign-category-update.component.ts">
</script>
