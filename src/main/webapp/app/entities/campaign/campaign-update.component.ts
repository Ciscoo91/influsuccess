import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import CampaignCategoryService from '../campaign-category/campaign-category.service';
import { ICampaignCategory } from '@/shared/model/campaign-category.model';

import SocialNetworkService from '../social-network/social-network.service';
import { ISocialNetwork } from '@/shared/model/social-network.model';

import UserService from '@/admin/user-management/user-management.service';

import AlertService from '@/shared/alert/alert.service';
import { ICampaign, Campaign } from '@/shared/model/campaign.model';
import CampaignService from './campaign.service';

const validations: any = {
  campaign: {
    langKey: {
      required,
    },
    title: {
      required,
    },
    description: {
      required,
    },
    status: {
      required,
    },
    minFollowers: {
      required,
      numeric,
    },
    maxFollowers: {
      required,
      numeric,
    },
    targetCountries: {},
  },
};

@Component({
  validations,
})
export default class CampaignUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('campaignService') private campaignService: () => CampaignService;
  public campaign: ICampaign = new Campaign();

  @Inject('campaignCategoryService') private campaignCategoryService: () => CampaignCategoryService;

  public campaignCategories: ICampaignCategory[] = [];

  @Inject('socialNetworkService') private socialNetworkService: () => SocialNetworkService;

  public socialNetworks: ISocialNetwork[] = [];

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.campaignId) {
        vm.retrieveCampaign(to.params.campaignId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.campaign.id) {
      this.campaignService()
        .update(this.campaign)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.campaign.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.campaignService()
        .create(this.campaign)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.campaign.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveCampaign(campaignId): void {
    this.campaignService()
      .find(campaignId)
      .then(res => {
        this.campaign = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.campaignCategoryService()
      .retrieve()
      .then(res => {
        this.campaignCategories = res.data;
      });
    this.socialNetworkService()
      .retrieve()
      .then(res => {
        this.socialNetworks = res.data;
      });
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
