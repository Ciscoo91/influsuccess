<template>
  <div class="shadow-lg col-md-2 d-flex flex-column justify-content-between nav-container">
    <nav class="sidenav d-flex flex-md-column flex-sm-row justify-content-between align-items-stretch aling-sm-items-center">
      <button
        v-for="item in menuItems"
        :key="item"
        class="bg-transparent side-nav-item text-primary mx-2 py-3 border-0"
        @click="onClickItem(item)"
      >
      <template v-if=" item === 'discussions' && totalNewMessageCount>0">
        <div class="row d-flex align-items-baseline justify-content-center">
          <p class="m-0 mr-1">
          {{ item.charAt(0).toUpperCase() + item.slice(1) }}
          </p>
        <small
              class="badge badge-danger text-white d-flex justify-content-center align-items-center my-0"
              >{{totalNewMessageCount}}</small
            >
        </div>
      </template>
      <template v-else>
        {{ item.charAt(0).toUpperCase() + item.slice(1) }}
      </template>
      </button>
    </nav>
  </div>
</template>

<script lang="ts" src="./sideNavBar.component.ts"></script>

<style scoped>
.side-nav-item {
  outline: none;
}

.side-nav-item:hover {
  background: #bbdefb;
}

@media screen and (min-width: 768px) {
  
  .nav-container{
    min-height: 100vh;
  }

}
</style>
