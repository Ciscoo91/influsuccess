import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import InfluencerService from '../influencer/influencer.service';
import { Influencer } from '@/shared/model/influencer.model';

import CampaignService from '../campaign/campaign.service';
import { ICampaign } from '@/shared/model/campaign.model';

import AlertService from '@/shared/alert/alert.service';
import { ICampaignCategory, CampaignCategory } from '@/shared/model/campaign-category.model';
import CampaignCategoryService from './campaign-category.service';

const validations: any = {
  campaignCategory: {
    name: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class CampaignCategoryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('campaignCategoryService') private campaignCategoryService: () => CampaignCategoryService;
  public campaignCategory: ICampaignCategory = new CampaignCategory();

  @Inject('influencerService') private influencerService: () => InfluencerService;

  public influencers: Influencer[] = [];

  @Inject('campaignService') private campaignService: () => CampaignService;

  public campaigns: ICampaign[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.campaignCategoryId) {
        vm.retrieveCampaignCategory(to.params.campaignCategoryId);
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
    if (this.campaignCategory.name) {
      this.campaignCategoryService()
        .update(this.campaignCategory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.campaignCategory.updated', { param: param.name });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.campaignCategoryService()
        .create(this.campaignCategory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.campaignCategory.created', { param: param.name });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveCampaignCategory(campaignCategoryId): void {
    this.campaignCategoryService()
      .find(campaignCategoryId)
      .then(res => {
        this.campaignCategory = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.influencerService()
      .retrieve()
      .then(res => {
        this.influencers = res.data;
      });
    this.campaignService()
      .retrieve()
      .then(res => {
        this.campaigns = res.data;
      });
  }
}
