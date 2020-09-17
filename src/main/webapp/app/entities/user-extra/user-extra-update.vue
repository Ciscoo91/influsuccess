<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="influSuccessApp.userExtra.home.createOrEditLabel" v-text="$t('influSuccessApp.userExtra.home.createOrEditLabel')">Create or edit a UserExtra</h2>
                <div>
                    <div class="form-group" v-if="userExtra.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="userExtra.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.userExtra.country')" for="user-extra-country">Country</label>
                        <input type="text" class="form-control" name="country" id="user-extra-country"
                            :class="{'valid': !$v.userExtra.country.$invalid, 'invalid': $v.userExtra.country.$invalid }" v-model="$v.userExtra.country.$model"  required/>
                        <div v-if="$v.userExtra.country.$anyDirty && $v.userExtra.country.$invalid">
                            <small class="form-text text-danger" v-if="!$v.userExtra.country.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.userExtra.birthday')" for="user-extra-birthday">Birthday</label>
                        <div class="d-flex">
                            <input id="user-extra-birthday" type="datetime-local" class="form-control" name="birthday" :class="{'valid': !$v.userExtra.birthday.$invalid, 'invalid': $v.userExtra.birthday.$invalid }"
                             required
                            :value="convertDateTimeFromServer($v.userExtra.birthday.$model)"
                            @change="updateInstantField('birthday', $event)"/>
                        </div>
                        <div v-if="$v.userExtra.birthday.$anyDirty && $v.userExtra.birthday.$invalid">
                            <small class="form-text text-danger" v-if="!$v.userExtra.birthday.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.userExtra.birthday.ZonedDateTimelocal" v-text="$t('entity.validation.ZonedDateTimelocal')">
                                This field should be a date and time.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.userExtra.phone')" for="user-extra-phone">Phone</label>
                        <input type="number" class="form-control" name="phone" id="user-extra-phone"
                            :class="{'valid': !$v.userExtra.phone.$invalid, 'invalid': $v.userExtra.phone.$invalid }" v-model.number="$v.userExtra.phone.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.userExtra.role')" for="user-extra-role">Role</label>
                        <select class="form-control" name="role" :class="{'valid': !$v.userExtra.role.$invalid, 'invalid': $v.userExtra.role.$invalid }" v-model="$v.userExtra.role.$model" id="user-extra-role"  required>
                            <option value="ADMIN" v-bind:label="$t('influSuccessApp.Role.ADMIN')">ADMIN</option>
                            <option value="INFLUENCER" v-bind:label="$t('influSuccessApp.Role.INFLUENCER')">INFLUENCER</option>
                            <option value="ADVERTISER" v-bind:label="$t('influSuccessApp.Role.ADVERTISER')">ADVERTISER</option>
                        </select>
                        <div v-if="$v.userExtra.role.$anyDirty && $v.userExtra.role.$invalid">
                            <small class="form-text text-danger" v-if="!$v.userExtra.role.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('influSuccessApp.userExtra.user')" for="user-extra-user">User</label>
                        <select class="form-control" id="user-extra-user" name="user" v-model="userExtra.user">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="userExtra.user && userOption.id === userExtra.user.id ? userExtra.user : userOption" v-for="userOption in users" :key="userOption.id">{{userOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.userExtra.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./user-extra-update.component.ts">
</script>
