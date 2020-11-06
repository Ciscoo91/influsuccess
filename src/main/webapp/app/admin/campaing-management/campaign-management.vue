<template>
    <div class="d-flex flex-column align-items-center">
        <h2 class="mt-4 align-self-start">Campagnes</h2>
        <div class="text-center" v-if="isFetching">
            <b-spinner label="Spinning"></b-spinner>
        </div>
        <form @submit.prevent="onSubmit" class="d-flex justify-content-between align-items-center w-50 mt-5">
            <b-form-group>
                <label for="perPage">Items per page</label>
                <b-form-select v-model="perPage" :options="optionsPerPage" id="perPage"/>
            </b-form-group>
            <b-form-group>
                <label for="title">Filter by title</label>
                <b-form-input placeholder="Search by title" v-model="title" id="title" />
            </b-form-group>
            <b-form-group>
                <label for="userLogin">Filter by user login</label>
                <b-form-input placeholder="Search by user login" v-model="userLogin" id="userLogin" />
            </b-form-group>
            <b-form-group>
                <button type="submit" class="btn btn-primary mt-4">Submit</button>
            </b-form-group>
        </form>



        <b-table
        id = "campaignTable"
        v-if="!isFetching"
        striped
        show-empty
        :items="campaigns"
        :current-page="currentPage"
        :filter-included-fields="fields"
        ></b-table>
                <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="campaignTable"
            @change="pageChange"
        ></b-pagination>
        
    </div>
</template>
<script src="./campaign-management.component.ts"></script>
