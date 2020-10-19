<template>
  <div class="home h-100">
    <div class="col-md-12 px-0">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-auto">
        <div class="container d-flex flex-column justify-content-around justify-content-center mt-5 h-100 vw-80 min-vh-100 mx-0 mt-sm-5">
          <div class="d-flex flex-md-row flex-column justify-content-between">
            <div class="title mt-5 col-md-6 col-sm-12">
              <h1 class="display-4 text-left font-weight-bold text-white" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-white text-left" v-text="$t('home.subtitle')">This is your homepage</p>
              <div>
                <a class="btn btn-outline-primary" href="#services">Our services</a>
              </div>
            </div>
            <div class="mt-5 col-md-6 col-sm-12 d-flex justify-content-end align-items-start" v-if="!authenticated">
              <div class="login-form col-md-8 bg-white w-100 mr-0 px-4 py-4 rounded-lg shadow-lg w-100">
                <form class="d-flex flex-column align-items-start" role="form" v-on:submit.prevent="doLogin()">
                  <div class="d-flex border-bottom border-secondary align-items-center justify-content-between py-2 w-100 mb-3">
                    <h3 class="py-2 w-100 mb-3 py-2">Sign In</h3>
                    <div class="">
                      <!-- <span class="text" v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span> -->
                      <a href="'/register'" class="btn btn-outline-primary w-100" v-text="$t('global.messages.info.register.link')"
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
          <div class="mt-4">
            <div class="container d-flex flex-md-row flex-sm-column justify-content-between w-100">
              <div class="card mb-3 bg-transparent border-0" style="max-width: 540px;" id="influencer">
                <div class="row g-0">
                  <div class="col-md-4">
                    <img src="https://source.unsplash.com/hCjo_bIkVig/200x200" alt="influencer_picture" />
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title text-white">Influenceurs</h5>
                      <p class="card-text text-white">
                        This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little
                        bit longer.
                      </p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card mb-3 bg-transparent border-0" style="max-width: 540px;" id="annonceur">
                <div class="row g-0">
                  <div class="col-md-4">
                    <img src="https://source.unsplash.com/Oalh2MojUuk/200x200" alt="business_meeting_picture" />
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title text-white">Annonceurs</h5>
                      <p class="card-text text-white">
                        This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little
                        bit longer.
                      </p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row services min-vh-50 w-100 mx-0 flex-column align-items-center mb-4" id="services">
        <h3 class="my-4">Our Services</h3>
        <div class="container">
          <div class="row d-flex flex-md-row flex-sm-column justify-content-around w-100">
            <b-card
              title="Card Title"
              img-src="https://source.unsplash.com/PN60YCp4Cu4"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
            <b-card
              title="Card Title"
              img-src="https://source.unsplash.com/PN60YCp4Cu4"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
            <b-card
              title="Card Title"
              img-src="https://source.unsplash.com/PN60YCp4Cu4"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
              </b-card-text>

              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card>
          </div>
        </div>
      </div>
      <div class="row d-flex justify-content-around flex-md-row flex-sm-column bg-dark">
        <div class="my-5">
          <div class="alert alert-success" v-if="authenticated">
            <span v-if="username" v-text="$t('home.logged.message', { username: username })"
              >You are logged in as user "{{ username }}"</span
            >
          </div>

          <div class="alert alert-warning" v-if="!authenticated">
            <span v-text="$t('global.messages.info.authenticated.prefix')">If you want to </span>
            <a class="alert-link" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">sign in</a
            ><span v-html="$t('global.messages.info.authenticated.suffix')"
              >, you can try the default accounts:<br />- Administrator (login="admin" and password="admin") <br />- User (login="user" and
              password="user").</span
            >
          </div>
          <div class="alert alert-warning" v-if="!authenticated">
            <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>&nbsp;
            <router-link class="alert-link" to="/register" v-text="$t('global.messages.info.register.link')"
              >Register a new account</router-link
            >
          </div>

          <p v-text="$t('home.question')" class="text-white">
            If you have any question on JHipster:
          </p>
        </div>
      </div>
      <!-- <ul>
        <li><a href="https://www.jhipster.tech/" target="_blank" rel="noopener" v-text="$t('home.link.homepage')">JHipster homepage</a></li>
        <li>
          <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener" v-text="$t('home.link.stackoverflow')"
            >JHipster on Stack Overflow</a
          >
        </li>
        <li>
          <a
            href="https://github.com/jhipster/generator-jhipster/issues?state=open"
            target="_blank"
            rel="noopener"
            v-text="$t('home.link.bugtracker')"
            >JHipster bug tracker</a
          >
        </li>
        <li>
          <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener" v-text="$t('home.link.chat')"
            >JHipster public chat room</a
          >
        </li>
        <li>
          <a href="https://twitter.com/jhipster" target="_blank" rel="noopener" v-text="$t('home.link.follow')"
            >follow @jhipster on Twitter</a
          >
        </li>
      </ul> -->

      <!-- <p>
        <span v-text="$t('home.like')">If you like JHipster, don't forget to give us a star on</span>
        <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener" v-text="$t('home.github')">GitHub</a>!
      </p> -->
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
  background-image: url('https://source.unsplash.com/FlPc9_VocJ4/');
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
</style>