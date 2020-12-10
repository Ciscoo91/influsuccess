import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInfluencer } from '@/shared/model/influencer.model';
import InfluencerService from './influencer.service';

@Component
export default class InfluencerDetails extends Vue {
  @Inject('influencerService') private influencerService: () => InfluencerService;
  public influencer: IInfluencer = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.influencerId) {
        vm.retrieveInfluencer(to.params.influencerId);
      }
    });
  }

  public retrieveInfluencer(influencerId) {
    this.influencerService()
      .find(influencerId)
      .then(res => {
        this.influencer = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
