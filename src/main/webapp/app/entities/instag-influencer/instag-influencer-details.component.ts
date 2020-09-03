import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInstagInfluencer } from '@/shared/model/instag-influencer.model';
import InstagInfluencerService from './instag-influencer.service';

@Component
export default class InstagInfluencerDetails extends Vue {
  @Inject('instagInfluencerService') private instagInfluencerService: () => InstagInfluencerService;
  public instagInfluencer: IInstagInfluencer = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.instagInfluencerId) {
        vm.retrieveInstagInfluencer(to.params.instagInfluencerId);
      }
    });
  }

  public retrieveInstagInfluencer(instagInfluencerId) {
    this.instagInfluencerService()
      .find(instagInfluencerId)
      .then(res => {
        this.instagInfluencer = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
