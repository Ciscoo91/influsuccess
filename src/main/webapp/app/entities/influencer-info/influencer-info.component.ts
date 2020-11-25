import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInfluencer } from '@/shared/model/influencer-info.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InfluencerService from './influencer-info.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Influencer extends mixins(AlertMixin) {
  @Inject('InfluencerService') private influencerService: () => InfluencerService;
  private removeId: number = null;

  public influencers: IInfluencer[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInfluencers();
  }

  public clear(): void {
    this.retrieveAllInfluencers();
  }

  public retrieveAllInfluencers(): void {
    this.isFetching = true;

    this.influencerService()
      .retrieve()
      .then(
        res => {
          this.influencers = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInfluencer): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInfluencer(): void {
    this.influencerService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.influencer.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInfluencers();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
