import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import SocialNetworkService from '../social-network/social-network.service';
import { ISocialNetwork } from '@/shared/model/social-network.model';

import InfluencerService from '../influencer/influencer.service';
import { IInfluencer } from '@/shared/model/influencer.model';

import AlertService from '@/shared/alert/alert.service';
import { ISocialNetworkLink, SocialNetworkLink } from '@/shared/model/social-network-link.model';
import SocialNetworkLinkService from './social-network-link.service';

const validations: any = {
  socialNetworkLink: {
    link: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class SocialNetworkLinkUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('socialNetworkLinkService') private socialNetworkLinkService: () => SocialNetworkLinkService;
  public socialNetworkLink: ISocialNetworkLink = new SocialNetworkLink();

  @Inject('socialNetworkService') private socialNetworkService: () => SocialNetworkService;

  public socialNetworks: ISocialNetwork[] = [];

  @Inject('influencerService') private influencerService: () => InfluencerService;

  public influencers: IInfluencer[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.socialNetworkLinkId) {
        vm.retrieveSocialNetworkLink(to.params.socialNetworkLinkId);
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
    if (this.socialNetworkLink.id) {
      this.socialNetworkLinkService()
        .update(this.socialNetworkLink)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.socialNetworkLink.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.socialNetworkLinkService()
        .create(this.socialNetworkLink)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.socialNetworkLink.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSocialNetworkLink(socialNetworkLinkId): void {
    this.socialNetworkLinkService()
      .find(socialNetworkLinkId)
      .then(res => {
        this.socialNetworkLink = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.socialNetworkService()
      .retrieve()
      .then(res => {
        this.socialNetworks = res.data;
      });
    this.influencerService()
      .retrieve()
      .then(res => {
        this.influencers = res.data;
      });
  }
}
