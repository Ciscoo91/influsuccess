import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICampaign } from '@/shared/model/campaign.model';
import CampaignService from './campaign.service';

@Component
export default class CampaignDetails extends Vue {
  @Inject('campaignService') private campaignService: () => CampaignService;
  public campaign: ICampaign = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.campaignId) {
        vm.retrieveCampaign(to.params.campaignId);
      }
    });
  }

  public retrieveCampaign(campaignId) {
    this.campaignService()
      .find(campaignId)
      .then(res => {
        this.campaign = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
