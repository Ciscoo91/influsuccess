import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInfluencerInfo } from '@/shared/model/influencer-info.model';
import InfluencerInfoService from './influencer-info.service';

@Component
export default class InfluencerInfoDetails extends Vue {
  @Inject('influencerInfoService') private influencerInfoService: () => InfluencerInfoService;
  public influencerInfo: IInfluencerInfo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.influencerInfoId) {
        vm.retrieveInfluencerInfo(to.params.influencerInfoId);
      }
    });
  }

  public retrieveInfluencerInfo(influencerInfoId) {
    this.influencerInfoService()
      .find(influencerInfoId)
      .then(res => {
        this.influencerInfo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
