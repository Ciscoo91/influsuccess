import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';
import SocialNetworkLinkService from './social-network-link.service';

@Component
export default class SocialNetworkLinkDetails extends Vue {
  @Inject('socialNetworkLinkService') private socialNetworkLinkService: () => SocialNetworkLinkService;
  public socialNetworkLink: ISocialNetworkLink = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.socialNetworkLinkId) {
        vm.retrieveSocialNetworkLink(to.params.socialNetworkLinkId);
      }
    });
  }

  public retrieveSocialNetworkLink(socialNetworkLinkId) {
    this.socialNetworkLinkService()
      .find(socialNetworkLinkId)
      .then(res => {
        this.socialNetworkLink = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
