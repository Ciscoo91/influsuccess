<template>
  <div class="form col-10">
    <div class="container d-flex flex-column">
      <form class="row mt-5 needs-validation m-auto d-flex justify-content-center" novalidate v-on:submit.prevent="onSubmit">
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
              <button type="submit" class="btn btn-primary" @click.prevent="onSubmit()">Submit</button>
            </div>
        </div>
      </form>
      <div id="searchResult">
        <b-spinner v-if="isLoading" variant="primary" type="grow" label="Spinning" class="align-self-center"></b-spinner>
        <div class="row my-2  d-flex justify-content-around" v-if="showResults">
          <b-card class="col-md-5 mx-1 my-3 bg-transparent border-0 shadow-lg" style="min-width: 18rem;" v-for="item in results" :key="item.id">
            <b-card-body class="row">
              <div class="col-md-6 col-sm-6">
                <h5 class="card-title">@{{ item.username }}</h5>
                <b-card-text>{{item.totalFollowers}} followers</b-card-text>
                <b-card-text>Categories: <span>[{{item.campaignCategories}}] </span></b-card-text>
                <b-card-text>{{item.country}}</b-card-text>
                <b-card-text>
                  <p>Social medias: </p>
                  <p v-for="sm in item.socialNetworkLinks" :key="sm.socialNetworkName">
                    <span v-if="sm.socialNetworkName == 'Instagram'">
                      <font-awesome-icon :icon="['fab', 'instagram']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'Twitter'">
                      <font-awesome-icon :icon="['fab', 'twitter']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'Pinterest'">
                      <font-awesome-icon :icon="['fab', 'pinterest']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'LinkedIn'">
                      <font-awesome-icon :icon="['fab', 'linkedin-in']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'Snapchat'">
                      <font-awesome-icon :icon="['fab', 'snapchat']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'Youtube'">
                      <font-awesome-icon :icon="['fab', 'youtube']" size="lg"></font-awesome-icon>
                    </span>
                    <span v-if="sm.socialNetworkName == 'Tiktok'">
                      <font-awesome-icon :icon="['fab', 'tiktok']" size="lg"></font-awesome-icon>
                    </span>
                  </p>
                </b-card-text>
              </div>
              <div class="col-md-6 col-sm-6">
                <span>Engagement rate: </span>
                <div class="engagement-rate d-flex justify-content-center align-items-center mt-1">
                  <font-awesome-icon icon="chart-pie" size="2x"></font-awesome-icon>
                  <span class="ml-3">{{item.rateEngagement || "70%"}}</span>
                </div>
              </div>
            </b-card-body>
            <b-card-footer class="text-right border-0 bg-transparent">
              <button class="btn btn-info">Begin a conversation</button>
            </b-card-footer>
          </b-card>
        </div>
        <b-pagination size="md" :total-rows="totalCards" v-model="currentPage" align="center" pills :change="loadPage(currentPage)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script src="./searchForm.component.ts">
</script>