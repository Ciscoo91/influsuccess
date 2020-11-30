<template>
    <div id="footer" class="footer py-3">
        <div class="container d-flex flex-md-row flex-sm-column justify-content-between align-sm-itmes-center">
            <p v-text="$t('footer')" class="text-muted">This is your footer</p>
            <p class="text-muted">Copyright &copy; 2020 | Influsucess</p>
        </div>
    </div>
</template>

<script lang="ts" src="./jhi-footer.component.ts">
</script>

<style scoped>
    .footer {
        text-align: left;
    }
</style>
