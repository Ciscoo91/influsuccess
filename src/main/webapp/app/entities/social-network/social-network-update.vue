<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.socialNetwork.home.createOrEditLabel" v-text="$t('influSuccessApp.socialNetwork.home.createOrEditLabel')">Create or edit a SocialNetwork</h2>
                <div>
                    <div class="form-group" v-if="socialNetwork.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="socialNetwork.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.socialNetwork.name')" for="social-network-name">Name</label>
                        <input type="text" class="form-control" name="name" id="social-network-name"
                            :class="{'valid': !$v.socialNetwork.name.$invalid, 'invalid': $v.socialNetwork.name.$invalid }" v-model="$v.socialNetwork.name.$model"  required/>
                        <div v-if="$v.socialNetwork.name.$anyDirty && $v.socialNetwork.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.socialNetwork.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.socialNetwork.campaign')" for="social-network-campaign">Campaign</label>
                        <select class="form-control" id="social-network-campaign" name="campaign" v-model="socialNetwork.campaign">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="socialNetwork.campaign && campaignOption.id === socialNetwork.campaign.id ? socialNetwork.campaign : campaignOption" v-for="campaignOption in campaigns" :key="campaignOption.id">{{campaignOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.socialNetwork.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./social-network-update.component.ts">
</script>
