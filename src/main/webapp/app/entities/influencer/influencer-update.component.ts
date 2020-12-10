import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import SocialNetworkLinkService from '../social-network-link/social-network-link.service';
import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';

import CampaignCategoryService from '../campaign-category/campaign-category.service';
import { ICampaignCategory } from '@/shared/model/campaign-category.model';

import AlertService from '@/shared/alert/alert.service';
import { IInfluencer, Influencer } from '@/shared/model/influencer.model';
import InfluencerService from './influencer.service';

const validations: any = {
  influencer: {},
};

@Component({
  validations,
})
export default class InfluencerUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('influencerService') private influencerService: () => InfluencerService;
  public influencer: IInfluencer = new Influencer();

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
      if (to.params.influencerId) {
        vm.retrieveInfluencer(to.params.influencerId);
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
    if (this.influencer.id) {
      this.influencerService()
        .update(this.influencer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.influencer.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.influencerService()
        .create(this.influencer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.influencer.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInfluencer(influencerId): void {
    this.influencerService()
      .find(influencerId)
      .then(res => {
        this.influencer = res;
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
