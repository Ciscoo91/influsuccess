import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICampaignCategory } from '@/shared/model/campaign-category.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import CampaignCategoryService from './campaign-category.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class CampaignCategory extends mixins(AlertMixin) {
  @Inject('campaignCategoryService') private campaignCategoryService: () => CampaignCategoryService;
  private removeId: number = null;

  public campaignCategories: ICampaignCategory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCampaignCategorys();
  }

  public clear(): void {
    this.retrieveAllCampaignCategorys();
  }

  public retrieveAllCampaignCategorys(): void {
    this.isFetching = true;

    this.campaignCategoryService()
      .retrieve()
      .then(
        res => {
          this.campaignCategories = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ICampaignCategory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCampaignCategory(): void {
    this.campaignCategoryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.campaignCategory.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllCampaignCategorys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
