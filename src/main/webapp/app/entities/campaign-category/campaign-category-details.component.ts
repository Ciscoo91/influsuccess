import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICampaignCategory } from '@/shared/model/campaign-category.model';
import CampaignCategoryService from './campaign-category.service';

@Component
export default class CampaignCategoryDetails extends Vue {
  @Inject('campaignCategoryService') private campaignCategoryService: () => CampaignCategoryService;
  public campaignCategory: ICampaignCategory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.campaignCategoryId) {
        vm.retrieveCampaignCategory(to.params.campaignCategoryId);
      }
    });
  }

  public retrieveCampaignCategory(campaignCategoryId) {
    this.campaignCategoryService()
      .find(campaignCategoryId)
      .then(res => {
        this.campaignCategory = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
