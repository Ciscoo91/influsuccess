<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.influencer.home.createOrEditLabel" v-text="$t('influSuccessApp.influencer.home.createOrEditLabel')">Create or edit a InfluencerInfo</h2>
                <div>
                    <div class="form-group" v-if="influencer.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="influencer.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.influencer.user')" for="influencer-info-user">User</label>
                        <select class="form-control" id="influencer-info-user" name="user" v-model="influencer.user">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="influencer.user && userOption.id === influencer.user.id ? influencer.user : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.influencer.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./influencer-info-update.component.ts">
</script>
