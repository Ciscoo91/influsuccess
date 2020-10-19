import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import SocialNetworkLinkService from '../social-network-link/social-network-link.service';
import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';

import CampaignCategoryService from '../campaign-category/campaign-category.service';
import { ICampaignCategory } from '@/shared/model/campaign-category.model';

import AlertService from '@/shared/alert/alert.service';
import { IInfluencerInfo, InfluencerInfo } from '@/shared/model/influencer-info.model';
import InfluencerInfoService from './influencer-info.service';

const validations: any = {
  influencerInfo: {},
};

@Component({
  validations,
})
export default class InfluencerInfoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('influencerInfoService') private influencerInfoService: () => InfluencerInfoService;
  public influencerInfo: IInfluencerInfo = new InfluencerInfo();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('socialNetworkLinkService') private socialNetworkLinkService: () => SocialNetworkLinkService;

  public socialNetworkLinks: ISocialNetworkLink[] = [];

  @Inject('campaignCategoryService') private campaignCategoryService: () => CampaignCategoryService;

  public campaignCategories: ICampaignCategory[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.influencerInfoId) {
        vm.retrieveInfluencerInfo(to.params.influencerInfoId);
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
    if (this.influencerInfo.id) {
      this.influencerInfoService()
        .update(this.influencerInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.influencerInfo.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.influencerInfoService()
        .create(this.influencerInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.influencerInfo.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInfluencerInfo(influencerInfoId): void {
    this.influencerInfoService()
      .find(influencerInfoId)
      .then(res => {
        this.influencerInfo = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.socialNetworkLinkService()
      .retrieve()
      .then(res => {
        this.socialNetworkLinks = res.data;
      });
    this.campaignCategoryService()
      .retrieve()
      .then(res => {
        this.campaignCategories = res.data;
      });
  }
}
