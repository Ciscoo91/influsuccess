import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICampaign } from '@/shared/model/campaign.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import CampaignService from './campaign.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Campaign extends mixins(AlertMixin) {
  @Inject('campaignService') private campaignService: () => CampaignService;
  private removeId: number = null;

  public campaigns: ICampaign[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCampaigns();
  }

  public clear(): void {
    this.retrieveAllCampaigns();
  }

  public retrieveAllCampaigns(): void {
    this.isFetching = true;

    this.campaignService()
      .retrieve()
      .then(
        res => {
          this.campaigns = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ICampaign): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCampaign(): void {
    this.campaignService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.campaign.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllCampaigns();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
