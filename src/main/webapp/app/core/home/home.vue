<template>
    <div class="home">
        <div class="col-md-12">
            <h1 class="display-4 text-center font-weight-bold " v-text="$t('home.title')">InfluSuccess!</h1>
            <p class="lead" v-text="$t('home.subtitle')">This is your homepage</p>

            <div>
                <div class="alert alert-success" v-if="authenticated">
                    <span v-if="username" v-text="$t('home.logged.message', { 'username': username})">You are logged in as user "{{username}}"</span>
                </div>

                <div class="alert alert-warning" v-if="!authenticated">
                    <span v-text="$t('global.messages.info.authenticated.prefix')">If you want to </span>
                    <a class="alert-link" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">sign in</a><span v-html="$t('global.messages.info.authenticated.suffix')">, you can try the default accounts:<br/>- Administrator (login="admin" and password="admin") <br/>- User (login="user" and password="user").</span>
                </div>
                <div class="alert alert-warning" v-if="!authenticated">
                    <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>&nbsp;
                    <router-link class="alert-link" to="/register" v-text="$t('global.messages.info.register.link')">Register a new account</router-link>
                </div>
            </div>

            <p v-text="$t('home.question')">
                If you have any question on JHipster:
            </p>

            <ul>
                <li><a href="https://www.jhipster.tech/" target="_blank" rel="noopener" v-text="$t('home.link.homepage')">JHipster homepage</a></li>
                <li><a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener" v-text="$t('home.link.stackoverflow')">JHipster on Stack Overflow</a></li>
                <li><a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener" v-text="$t('home.link.bugtracker')">JHipster bug tracker</a></li>
                <li><a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener" v-text="$t('home.link.chat')">JHipster public chat room</a></li>
                <li><a href="https://twitter.com/jhipster" target="_blank" rel="noopener" v-text="$t('home.link.follow')">follow @jhipster on Twitter</a></li>
            </ul>

            <p>
                <span v-text="$t('home.like')">If you like JHipster, don't forget to give us a star on</span> <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener" v-text="$t('home.github')">GitHub</a>!
            </p>
        </div>
    <div>
        <div  class="text-center" v-if="!authenticated">
            <b-button class="p-3 m-4 font-weight-bold" pill size="lg" variant="outline-primary" v-text="$t('home.signIn')" v-on:click="openLogin()">
               Sign in
            </b-button>
            <b-button class="p-3 m-4 font-weight-bold" pill size="lg" variant="outline-primary" v-text="$t('home.register')" v-on:click="$router.push('register')" >
                Register Now
            </b-button>
        </div>
        </div>
    </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>
