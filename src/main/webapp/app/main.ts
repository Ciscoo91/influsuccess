// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';

import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import InfiniteLoading from 'vue-infinite-loading';
import AuditsService from './admin/audits/audits.service';

import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from '@/admin/user-management/user-management.service';

import LoginService from './account/login.service';
import AccountService from './account/account.service';

import '../content/scss/vendor.scss';
import AlertService from '@/shared/alert/alert.service';
import TranslationService from '@/locale/translation.service';
import ConfigurationService from '@/admin/configuration/configuration.service';

/* tslint:disable */

import UserExtraService from '@/entities/user-extra/user-extra.service';
import CampaignService from '@/entities/campaign/campaign.service';
import AdvDashboardService from '@/dashboard/advertiser/advDashboard.service';
import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';
import SocialNetworkService from '@/entities/social-network/social-network.service';
import DiscussionService from '@/entities/discussion/discussion.service';
import MessageService from '@/entities/message/message.service';
import SocialNetworkLinkService from '@/entities/social-network-link/social-network-link.service';
import InfluencerService from '@/entities/influencer/influencer.service';
import InfluencerDashboardService from '@/dashboard/influencer/influencerDashboard.service';
// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Vue2Filters);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);

/* add fa to library font-awesone */
import { faEdit, faTable } from '@fortawesome/free-solid-svg-icons';
library.add(faEdit);
library.add(faTable);

const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const alertService = new AlertService(store);
const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userService: () => new UserManagementService(),

    auditsService: () => new AuditsService(),

    healthService: () => new HealthService(),

    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),
    alertService: () => alertService,
    translationService: () => translationService,
    campaignCategoryService: () => new CampaignCategoryService(),
    socialNetworkService: () => new SocialNetworkService(),
    userExtraService: () => new UserExtraService(),
    campaignService: () => new CampaignService(),
    influencerService: () => new InfluencerService(),
    socialNetworkLinkService: () => new SocialNetworkLinkService(),
    messageService: () => new MessageService(),
    discussionService: () => new DiscussionService(),
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
    advDashboardService: () => new AdvDashboardService(),
    influencerDashboardService: () => new InfluencerDashboardService(),
  },
  i18n,
  store,
});
