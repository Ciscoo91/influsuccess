import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import CampaignService from '../campaign/campaign.service';
import { ICampaign } from '@/shared/model/campaign.model';

import AlertService from '@/shared/alert/alert.service';
import { ISocialNetwork, SocialNetwork } from '@/shared/model/social-network.model';
import SocialNetworkService from './social-network.service';

const validations: any = {
  socialNetwork: {
    name: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class SocialNetworkUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('socialNetworkService') private socialNetworkService: () => SocialNetworkService;
  public socialNetwork: ISocialNetwork = new SocialNetwork();

  @Inject('campaignService') private campaignService: () => CampaignService;

  public campaigns: ICampaign[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.socialNetworkId) {
        vm.retrieveSocialNetwork(to.params.socialNetworkId);
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
    if (this.socialNetwork.name) {
      this.socialNetworkService()
        .update(this.socialNetwork)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.socialNetwork.updated', { param: param.name });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.socialNetworkService()
        .create(this.socialNetwork)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.socialNetwork.created', { param: param.name });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSocialNetwork(socialNetworkId): void {
    this.socialNetworkService()
      .find(socialNetworkId)
      .then(res => {
        this.socialNetwork = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.campaignService()
      .retrieve()
      .then(res => {
        this.campaigns = res.data;
      });
  }
}
