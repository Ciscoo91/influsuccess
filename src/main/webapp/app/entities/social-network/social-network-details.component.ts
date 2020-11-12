import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISocialNetwork } from '@/shared/model/social-network.model';
import SocialNetworkService from './social-network.service';

@Component
export default class SocialNetworkDetails extends Vue {
  @Inject('socialNetworkService') private socialNetworkService: () => SocialNetworkService;
  public socialNetwork: ISocialNetwork = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.socialNetworkId) {
        vm.retrieveSocialNetwork(to.params.socialNetworkId);
      }
    });
  }

  public retrieveSocialNetwork(socialNetworkId) {
    this.socialNetworkService()
      .find(socialNetworkId)
      .then(res => {
        this.socialNetwork = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
