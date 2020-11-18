<template>
  <div id="app" class="min-vh-100">
    <ribbon></ribbon>
    <div id="app-header">
      <jhi-navbar></jhi-navbar>
    </div>
    <div class="container-fluid mt-0 px-0">
      <div class="card mt-0 py-0 mx-0 px-0 jh-card">
        <router-view></router-view>
      </div>
      <b-modal id="login-page" hide-footer lazy>
        <span slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
        <login-form></login-form>
      </b-modal>
    </div>
    <jhi-footer></jhi-footer>
  </div>
</template>

<script lang="ts" src="./app.component.ts">
</script>
<style scoped>
.container-fluid {
  min-height: 100vh;
}

.card {
  min-height: 100vh;
}
</style>