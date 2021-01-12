<template>
  <div class="form col-10">
    <div class="container d-flex flex-column">
      <form class="row mt-5 needs-validation align-self-center" novalidate v-on:submit.prevent="onSubmit">
        <div class="row">
          <div class="col-md-3 mb-3">
            <label for="validationCustom01" class="form-label">Country</label>
            <b-form-select v-model="countrySelected" :options="countries"></b-form-select>
          </div>
          <div class="col-md-3 mb-3">
            <label for="validationCustom02" class="form-label">Platforms</label>
            <b-form-select v-model="paltformSelected" :options="platforms"></b-form-select>
          </div>
          <div class="col-md-3 mb-3">
            <label for="validationCustom03" class="form-label">Category</label>
            <b-form-select v-model="categorySelected" :options="categories"></b-form-select>
          </div>
        </div>
        <div class="row">
            <div class="col-md-3 mb-3">
              <label for="validationCustom03" class="form-label">Total followers min</label>
              <input type="number" class="form-control">
            </div>
            <div class="col-md-3 mb-3">
              <label for="validationCustom03" class="form-label">Items per page</label>
              <b-form-select v-model="itemsPerPageSelected" :options="itemsPerPage"></b-form-select>
            </div>
            <div class="col-md-3 mb-3">
              <label for="validationCustom03" class="form-label">Username</label>
              <input type="text" class="form-control">
            </div>
            <div class="col-md-3 d-flex align-items-center pt-3">
              <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
      </form>
      <div id="searchResult">
        <div class="row mt-4 d-flex justify-content-between" v-if="showResults">
          <b-card class="col-md-4 border-0 shadow-sm" style="width: 18rem;" v-for="item in results" :key="item.id">
            <b-card-body class="row">
              <div class="col-md-6 col-sm-6">
                <h5 class="card-title">{{ item.name }}</h5>
                <b-card-text>{{item.followers}} followers</b-card-text>
                <b-card-text>Catégories: <span v-for="category in item.categories" :key="category">[{{category}}] </span></b-card-text>
                <b-card-text>{{item.country}}</b-card-text>
                <b-card-text>Social medias: <span v-for="sm in item.socialMedias" :key="sm">[{{sm}}] </span></b-card-text>
              </div>
              <div class="col-md-6 col-sm-6">
                Engagement rate: 80%
                <font-awesome-icon icon="chart-pie" size="3x"></font-awesome-icon>
              </div>
            </b-card-body>
            <b-card-footer class="text-right border-0">
              <button class="btn btn-info">Begin a conversation</button>
            </b-card-footer>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./searchForm.component.ts">
</script>