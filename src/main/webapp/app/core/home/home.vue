<template>
  <div class="home h-100">
    <div class="col-md-12 px-0">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-auto">
        <div class="container d-flex flex-column justify-content-around justify-content-center mt-5 h-100 vw-80 min-vh-100 mx-0 mt-sm-5">
          <div class="d-flex flex-md-row flex-column justify-content-between">
            <div class="title mt-5 col-md-6 col-sm-12">
              <h1 class="display-4 text-left font-weight-bold text-white" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-white text-left" v-text="$t('home.subtitle')"></p>
            </div>
            <div class="mt-5 col-md-6 col-sm-12 d-flex justify-content-end align-items-start" v-if="!authenticated">
              <div class="login-form col-md-8 bg-white w-100 mr-0 px-4 py-4 rounded-lg shadow-lg w-100">
                <form class="d-flex flex-column align-items-start" role="form" v-on:submit.prevent="doLogin()">
                  <div class="d-flex border-bottom border-secondary align-items-center justify-content-between py-2 w-100 mb-3">
                    <h3 class="py-2 w-100 mb-3 py-2">Sign In</h3>
                    <div class="">
                      <!-- <span class="text" v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span> -->
                      <a href="/register" class="btn btn-outline-primary w-100" v-text="$t('global.messages.info.register.link')"
                        >Register a new account</a
                      >
                    </div>
                  </div>
                  <b-form-group class="w-100" v-bind:label="$t('global.form[\'username.label\']')" label-for="username">
                    <b-form-input
                      id="username"
                      type="text"
                      name="username"
                      autofocus
                      v-bind:placeholder="$t('global.form[\'username.placeholder\']')"
                      v-model="login"
                    >
                    </b-form-input>
                  </b-form-group>
                  <b-form-group class="w-100" v-bind:label="$t('login.form.password')" label-for="password">
                    <b-form-input
                      id="password"
                      type="password"
                      name="password"
                      v-model.trim="name"
                      v-bind:placeholder="$t('login.form[\'password.placeholder\']')"
                      v-model="password"
                    >
                    </b-form-input>
                  </b-form-group>
                  <b-form-checkbox class="my-2" id="rememberMe" name="rememberMe" v-model="rememberMe" checked>
                    <span v-text="$t('login.form.rememberme')">Remember me</span>
                  </b-form-checkbox>
                  <div class="form-group">
                    <b-button type="submit" variant="primary" v-text="$t('login.form.button')">Sign in</b-button>
                  </div>
                </form>
                <p></p>
                <div>
                  <div>
                    <b-link :to="'/account/reset/request'" class="alert-link" v-text="$t('login.password.forgot')"
                      >Did you forget your password?</b-link
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="my-5">
            <div class="container d-flex flex-md-row flex-sm-column justify-content-between w-100">
              <div class="card mb-3 bg-transparent border-0" style="max-width: 540px;" id="influencer">
                <div class="row g-0 d-flex">
                  <div class="col-md-4 align-self-stretch">
                    <img src="/content/images/homepage_influencer_phone_on_hand.jpg" alt="influencer_picture" style="width: 200px" class="rounded pt-4"/>
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title text-white">Influenceurs</h5>
                      <p class="card-text text-white">
                        Faites découvrir à votre audiance des produits et services nouveaux que vous avez découvert en collaborant avec une entreprise
                      </p>
                      <p class="card-text"><small class="text-muted"><a class="btn btn-outline-secondary" href="#description-section-influencer">En savoir plus</a></small></p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card mb-3 bg-transparent border-0" style="max-width: 540px;" id="annonceur">
                <div class="row g-0 d-flex align-items-start">
                  <div class="col-md-4">
                    <img src="/content/images/homepage_business_meeting.jpg" alt="business_meeting_picture" style="width: 200px" class="rounded pt-4"/>
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title text-white">Annonceurs</h5>
                      <p class="card-text text-white">
                        Renforcez l'image de votre marque auprès des consommateurs grâce à des influenceurs qui auront testé votre produit et qui témoignent de la qualité du produit.
                      </p>
                      <p class="card-text"><small class="text-muted"><a class="btn btn-outline-secondary" href="#description-section-campaign">En savoir plus</a></small></p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="description-section-container container mt-5">
        <div class="description-section-campaign row d-flex justify-content-between" id="description-section-campaign">
            <img src="/content/images/homepage_social_medias.jpg" class="description_social_marketing col-md-6" alt="">

            <div class="content col-md-6">
              <h3>Pourquoi investir dans le marketing d'influence ?</h3>
              <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ut, accusantium assumenda? Libero velit enim pariatur neque, nesciunt aperiam temporibus fuga sint, expedita animi veritatis! Dolorum autem nisi dicta sed veniam.
              Nesciunt, illum, necessitatibus id esse similique optio eligendi eaque, nobis blanditiis harum in. Libero quisquam atque repellat voluptatem quia assumenda repudiandae, nihil illum eaque dolores veniam veritatis, quo odit neque</p>
            </div>
        </div>
        <div class="description-section-influencer row d-flex justify-content-between my-5" id="description-section-influencer">
            <div class="content col-md-6">
              <h3>Pourquoi investir dans le marketing d'influence ?</h3>
              <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aspernatur corrupti vero tempore officia, maxime sint? Et, adipisci. Expedita, unde voluptatem eos ea minima voluptatum in vel neque nam consequatur dicta.
              </p>
            </div>
            <img src="/content/images/homepage_social_influencer.jpg" class="description_social_marketing col-md-6" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>

<style scoped>
.banner {
  min-height: 70vh;
  height: 100vh;
  position: relative;
  background-image: url('/content/images/homepage-banner.jpg');
  background-size: cover;
}

.banner:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}

.title {
  z-index: 2;
}

.title .lead {
  line-height: 1.2em;
}

.login-form {
  box-shadow: rgba(255, 255, 255, 0.7);
}

.description_social_marketing{
  width: 50%;
}

.content{
  line-height:2em;
}
</style>