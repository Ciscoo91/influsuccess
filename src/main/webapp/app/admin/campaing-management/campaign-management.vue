<template>
    <div>
        <h2>Campagnes</h2>
        <div class="text-center" v-if="isFetching">
            <b-spinner label="Spinning"></b-spinner>
        </div>
        <b-table striped show-empty :items="campaigns">
            <template slot="top-row" slot-scope="{fields}">
                <td v-for="field in fields" :key="field.id">
                    <input v-model="filters[field.key]" :placeholder="field.label">
                </td>
            </template>
        </b-table>
    </div>
</template>
<script src="./campaign-management.component.ts"></script>