<template>
  <div class="form col-10">
    <div class="container">
      <form class="row mt-5 needs-validation" novalidate v-on:submit.prevent="onSubmit">
        <div class="col-md-3 mb-3">
          <label for="validationCustom01" class="form-label">City</label>
          <select class="custom-select shadow-sm border-border-primary" id="validationCustom01" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
        </div>
        <div class="col-md-3 mb-3">
          <label for="validationCustom02" class="form-label">Platforms</label>
          <select class="custom-select shadow-sm border-border-primary" id="validationCustom02" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
        </div>
        <div class="col-md-3 mb-3">
          <label for="validationCustom03" class="form-label">Themes</label>
          <select class="custom-select shadow-sm border-border-primary" id="validationCustom03" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
        </div>
        <div class="col-md-3 d-flex align-items-center pt-3">
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </form>
      <div id="searchResult">
        <div class="row mt-4 d-flex justify-content-between" v-if="showResults">
          <div class="card col-md-3" style="width: 18rem;" v-for="item in results" :key="item.id">
            <img v-bind:src="item.image" class="card-img-top" v-bind:alt="item" />
            <div class="card-body">
              <h5 class="card-title">{{ item.name }}</h5>
              <p class="card-text">Social medias: {{ item.socialMedias }}</p>
              <a href="#" class="btn btn-primary">followers: {{ item.followers }}</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./searchForm.component.ts">
</script>