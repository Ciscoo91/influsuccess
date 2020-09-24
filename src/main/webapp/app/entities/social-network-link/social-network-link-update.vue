<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.socialNetworkLink.home.createOrEditLabel" v-text="$t('influSuccessApp.socialNetworkLink.home.createOrEditLabel')">Create or edit a SocialNetworkLink</h2>
                <div>
                    <div class="form-group" v-if="socialNetworkLink.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="socialNetworkLink.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.socialNetworkLink.link')" for="social-network-link-link">Link</label>
                        <input type="text" class="form-control" name="link" id="social-network-link-link"
                            :class="{'valid': !$v.socialNetworkLink.link.$invalid, 'invalid': $v.socialNetworkLink.link.$invalid }" v-model="$v.socialNetworkLink.link.$model"  required/>
                        <div v-if="$v.socialNetworkLink.link.$anyDirty && $v.socialNetworkLink.link.$invalid">
                            <small class="form-text text-danger" v-if="!$v.socialNetworkLink.link.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.socialNetworkLink.socialNetwork')" for="social-network-link-socialNetwork">Social Network</label>
                        <select class="form-control" id="social-network-link-socialNetwork" name="socialNetwork" v-model="socialNetworkLink.socialNetwork">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="socialNetworkLink.socialNetwork && socialNetworkOption.id === socialNetworkLink.socialNetwork.id ? socialNetworkLink.socialNetwork : socialNetworkOption" v-for="socialNetworkOption in socialNetworks" :key="socialNetworkOption.id">{{socialNetworkOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.socialNetworkLink.influencerInfo')" for="social-network-link-influencerInfo">Influencer Info</label>
                        <select class="form-control" id="social-network-link-influencerInfo" name="influencerInfo" v-model="socialNetworkLink.influencerInfo">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="socialNetworkLink.influencerInfo && influencerInfoOption.id === socialNetworkLink.influencerInfo.id ? socialNetworkLink.influencerInfo : influencerInfoOption" v-for="influencerInfoOption in influencerInfos" :key="influencerInfoOption.id">{{influencerInfoOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.socialNetworkLink.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./social-network-link-update.component.ts">
</script>
