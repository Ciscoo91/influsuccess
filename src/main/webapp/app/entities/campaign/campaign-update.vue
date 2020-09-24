<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.campaign.home.createOrEditLabel" v-text="$t('influSuccessApp.campaign.home.createOrEditLabel')">Create or edit a Campaign</h2>
                <div>
                    <div class="form-group" v-if="campaign.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="campaign.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.langKey')" for="campaign-langKey">Lang Key</label>
                        <select class="form-control" name="langKey" :class="{'valid': !$v.campaign.langKey.$invalid, 'invalid': $v.campaign.langKey.$invalid }" v-model="$v.campaign.langKey.$model" id="campaign-langKey"  required>
                            <option value="AR" v-bind:label="$t('influSuccessApp.LangKey.AR')">AR</option>
                            <option value="FR" v-bind:label="$t('influSuccessApp.LangKey.FR')">FR</option>
                            <option value="EN" v-bind:label="$t('influSuccessApp.LangKey.EN')">EN</option>
                            <option value="ES" v-bind:label="$t('influSuccessApp.LangKey.ES')">ES</option>
                            <option value="IT" v-bind:label="$t('influSuccessApp.LangKey.IT')">IT</option>
                            <option value="DE" v-bind:label="$t('influSuccessApp.LangKey.DE')">DE</option>
                        </select>
                        <div v-if="$v.campaign.langKey.$anyDirty && $v.campaign.langKey.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.langKey.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.title')" for="campaign-title">Title</label>
                        <input type="text" class="form-control" name="title" id="campaign-title"
                            :class="{'valid': !$v.campaign.title.$invalid, 'invalid': $v.campaign.title.$invalid }" v-model="$v.campaign.title.$model"  required/>
                        <div v-if="$v.campaign.title.$anyDirty && $v.campaign.title.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.title.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.description')" for="campaign-description">Description</label>
                        <input type="text" class="form-control" name="description" id="campaign-description"
                            :class="{'valid': !$v.campaign.description.$invalid, 'invalid': $v.campaign.description.$invalid }" v-model="$v.campaign.description.$model"  required/>
                        <div v-if="$v.campaign.description.$anyDirty && $v.campaign.description.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.description.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.status')" for="campaign-status">Status</label>
                        <select class="form-control" name="status" :class="{'valid': !$v.campaign.status.$invalid, 'invalid': $v.campaign.status.$invalid }" v-model="$v.campaign.status.$model" id="campaign-status"  required>
                            <option value="CLOSED" v-bind:label="$t('influSuccessApp.CampaignStatus.CLOSED')">CLOSED</option>
                            <option value="OPENED" v-bind:label="$t('influSuccessApp.CampaignStatus.OPENED')">OPENED</option>
                        </select>
                        <div v-if="$v.campaign.status.$anyDirty && $v.campaign.status.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.status.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.minFollowers')" for="campaign-minFollowers">Min Followers</label>
                        <input type="number" class="form-control" name="minFollowers" id="campaign-minFollowers"
                            :class="{'valid': !$v.campaign.minFollowers.$invalid, 'invalid': $v.campaign.minFollowers.$invalid }" v-model.number="$v.campaign.minFollowers.$model"  required/>
                        <div v-if="$v.campaign.minFollowers.$anyDirty && $v.campaign.minFollowers.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.minFollowers.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.campaign.minFollowers.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.maxFollowers')" for="campaign-maxFollowers">Max Followers</label>
                        <input type="number" class="form-control" name="maxFollowers" id="campaign-maxFollowers"
                            :class="{'valid': !$v.campaign.maxFollowers.$invalid, 'invalid': $v.campaign.maxFollowers.$invalid }" v-model.number="$v.campaign.maxFollowers.$model"  required/>
                        <div v-if="$v.campaign.maxFollowers.$anyDirty && $v.campaign.maxFollowers.$invalid">
                            <small class="form-text text-danger" v-if="!$v.campaign.maxFollowers.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.campaign.maxFollowers.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.targetCountries')" for="campaign-targetCountries">Target Countries</label>
                        <input type="text" class="form-control" name="targetCountries" id="campaign-targetCountries"
                            :class="{'valid': !$v.campaign.targetCountries.$invalid, 'invalid': $v.campaign.targetCountries.$invalid }" v-model="$v.campaign.targetCountries.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.campaign.user')" for="campaign-user">User</label>
                        <select class="form-control" id="campaign-user" name="user" v-model="campaign.user">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="campaign.user && userOption.id === campaign.user.id ? campaign.user : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.campaign.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./campaign-update.component.ts">
</script>
