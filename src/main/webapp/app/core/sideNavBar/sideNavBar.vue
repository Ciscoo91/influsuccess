<template>
  <div class="shadow-lg min-vh-100 col-2 d-flex flex-column justify-content-between">
    <nav class="sidenav d-flex flex-column">
      <button
        v-for="item in menuItems"
        :key="item"
        class="bg-white side-nav-item text-primary mx-2 py-3 border-0"
        @click="onClickItem(item)"
      >
        {{ item.charAt(0).toUpperCase() + item.slice(1) }}
      </button>
    </nav>
    <button class="bg-white text-dark border-0">Contact</button>
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
</style>
