import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInfluencerInfo } from '@/shared/model/influencer-info.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InfluencerInfoService from './influencer-info.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class InfluencerInfo extends mixins(AlertMixin) {
  @Inject('influencerInfoService') private influencerInfoService: () => InfluencerInfoService;
  private removeId: number = null;

  public influencerInfos: IInfluencerInfo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInfluencerInfos();
  }

  public clear(): void {
    this.retrieveAllInfluencerInfos();
  }

  public retrieveAllInfluencerInfos(): void {
    this.isFetching = true;

    this.influencerInfoService()
      .retrieve()
      .then(
        res => {
          this.influencerInfos = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInfluencerInfo): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInfluencerInfo(): void {
    this.influencerInfoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.influencerInfo.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInfluencerInfos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
