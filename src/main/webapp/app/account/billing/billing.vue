<template>
    <div id="checkout" class="container mt-5" v-on:changeBillingComponent="onChangeStep">
        <keep-alive>
            <component :is="currentComponent"></component>
        </keep-alive>
    </div>
</template>
<script src="https://js.stripe.com/v3/"></script>
<script lang="ts" src="./billing.component.ts"></script>