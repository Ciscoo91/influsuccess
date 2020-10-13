<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="userExtra">
                <h2 class="jh-entity-heading"><span v-text="$t('influSuccessApp.userExtra.detail.title')">UserExtra</span> {{userExtra.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('influSuccessApp.userExtra.country')">Country</span>
                    </dt>
                    <dd>
                        <span>{{userExtra.country}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.userExtra.birthday')">Birthday</span>
                    </dt>
                    <dd>
                        <span>{{userExtra.birthday}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.userExtra.phone')">Phone</span>
                    </dt>
                    <dd>
                        <span>{{userExtra.phone}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('influSuccessApp.userExtra.user')">User</span>
                    </dt>
                    <dd>
                        {{userExtra.user ? userExtra.user.id : ''}}
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="userExtra.id" :to="{name: 'UserExtraEdit', params: {userExtraId: userExtra.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./user-extra-details.component.ts">
</script>
