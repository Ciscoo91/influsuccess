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
