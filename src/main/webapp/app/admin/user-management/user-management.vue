<template>
  <div class="d-flex flex-column">
    <h2 class="ml-3 mt-3">
      <span id="user-management-page-heading" v-text="$t('userManagement.home.title')">Users</span>
      <router-link tag="button" class="btn btn-primary float-right jh-create-entity align-self-end mt-3 mr-3" :to="{ name: 'JhiUserCreate' }">
        <font-awesome-icon icon="plus"></font-awesome-icon> <span v-text="$t('userManagement.home.createLabel')">Create a new User</span>
      </router-link>
    </h2>
    <b-alert
      :show="dismissCountDown"
      dismissible
      :variant="alertType"
      @dismissed="dismissCountDown = 0"
      @dismiss-count-down="countDownChanged"
    >
      {{ alertMessage }}
    </b-alert>
    <form @submit.prevent="onSubmit" class="d-flex justify-content-around align-self-center w-100 my-4">
        <b-form-group>
            <label for="perPage">Items per page</label>
            <b-form-select v-model="itemsPerPage" :options="optionsPerPage" id="perPage"/>
        </b-form-group>
        <b-form-group>
            <label for="title">Filter by email</label>
            <b-form-input placeholder="Search by email" v-model="email" id="email" />
        </b-form-group>
        <b-form-group>
            <label for="userLogin">Filter by user login</label>
            <b-form-input placeholder="Search by user login" v-model="login" id="userLogin" />
        </b-form-group>
        <b-form-group>
            <label for="userLogin">Filter by user firstname</label>
            <b-form-input placeholder="Search by user firstname" v-model="firstName" id="userLogin" />
        </b-form-group>
        <b-form-group>
            <label for="userLogin">Filter by user lastname</label>
            <b-form-input placeholder="Search by user lastname" v-model="lastName" id="userLogin" />
        </b-form-group>
        <b-form-group class="mt-2">
            <button type="submit" class="btn btn-primary mt-4">Search</button>
        </b-form-group>
    </form>
    <div class="table-responsive" v-if="users">
      <table class="table table-striped">
        <thead>
          <tr>
            <th v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th v-on:click="changeOrder('login')">
              <span v-text="$t('userManagement.login')">Login</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'login'"></jhi-sort-indicator>
            </th>
            <th v-on:click="changeOrder('email')">
              <span v-text="$t('userManagement.email')">Email</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th></th>
            <th v-on:click="changeOrder('langKey')">
              <span v-text="$t('userManagement.langKey')">Lang Key</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'langKey'"></jhi-sort-indicator>
            </th>
            <th><span v-text="$t('userManagement.profiles')">Profiles</span></th>
            <th v-on:click="changeOrder('createdDate')">
              <span v-text="$t('userManagement.createdDate')">Created Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdDate'"></jhi-sort-indicator>
            </th>
            <th v-on:click="changeOrder('lastModifiedBy')">
              <span v-text="$t('userManagement.lastModifiedBy')">Last Modified By</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedBy'"></jhi-sort-indicator>
            </th>
            <th id="modified-date-sort" v-on:click="changeOrder('lastModifiedDate')">
              <span v-text="$t('userManagement.lastModifiedDate')">Last Modified Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastModifiedDate'"></jhi-sort-indicator>
            </th>
            <th></th>
          </tr>
        </thead>
        <tbody v-if="users">
          <tr v-for="user in users" :key="user.id" :id="user.login">
            <td>
              <router-link tag="a" :to="{ name: 'JhiUserView', params: { userId: user.login } }">{{ user.id }}</router-link>
            </td>
            <td>{{ user.login }}</td>
            <td class="jhi-user-email">{{ user.email }}</td>
            <td>
              <button
                class="btn btn-danger btn-sm deactivated"
                v-on:click="setActive(user, true)"
                v-if="!user.activated"
                v-text="$t('userManagement.deactivated')"
              >
                Deactivated
              </button>
              <button
                class="btn btn-success btn-sm"
                v-on:click="setActive(user, false)"
                v-if="user.activated"
                :disabled="username === user.login"
                v-text="$t('userManagement.activated')"
              >
                Activated
              </button>
            </td>
            <td>{{ user.langKey }}</td>
            <td>
              <div v-for="authority of user.authorities" :key="authority">
                <span class="badge badge-info">{{ authority }}</span>
              </div>
            </td>
            <td v-if="user.createdDate">{{ $d(Date.parse(user.createdDate), 'short') }}</td>
            <td v-else>{{ user.createdDate | formatDate }}</td>
            <td>{{ user.lastModifiedBy }}</td>
            <td v-if="user.lastModifiedDate">{{ $d(Date.parse(user.lastModifiedDate), 'short') }}</td>
            <td v-else>{{ user.lastModifiedDate | formatDate }}</td>
            <td class="text-right">
              <div class="btn-group">
                <b-button v-b-modal.modal-email @click="prepareUserForEmail(user)">
                  send email
                </b-button>
                <router-link :to="{ name: 'JhiUserView', params: { userId: user.login } }" tag="button" class="btn btn-info btn-sm details">
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
                <router-link :to="{ name: 'JhiUserEdit', params: { userId: user.login } }" tag="button" class="btn btn-primary btn-sm edit">
                  <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                </router-link>
                <b-button v-on:click="prepareRemove(user)" variant="danger" class="btn btn-sm delete" :disabled="username === user.login">
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <b-modal
        ref="removeUser"
        id="removeUser"
        v-bind:title="$t('entity.delete.title')"
        @ok="deleteUser()"
      >
        <div class="modal-body">
          <p id="jhi-delete-user-heading" v-text="$t('userManagement.delete.question', { login: removeId })">
            Are you sure you want to delete this user?
          </p>
        </div>
        <div slot="modal-footer">
          <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="confirm-delete-user"
            v-text="$t('entity.action.delete')"
            v-on:click="deleteUser()"
          >
            Delete
          </button>
        </div>
      </b-modal>
      <b-modal id="modal-email" title="Send mail">
        <form @submit.prevent="sendMail">
          <b-form-textarea
          id="textarea"
          v-model="emailContent"
          placeholder="Enter something..."
          rows="6"
          max-rows="10"
          />
          <b-form-group class="mt-4">
            <b-button variant="info" type="submit">Send Mail</b-button>
          </b-form-group>
        </form>
      </b-modal>
    </div>
    <div v-show="users && users.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./user-management.component.ts">
</script>
