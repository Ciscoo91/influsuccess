import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IInstagInfluencer } from '@/shared/model/instag-influencer.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import InstagInfluencerService from './instag-influencer.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class InstagInfluencer extends mixins(AlertMixin) {
  @Inject('instagInfluencerService') private instagInfluencerService: () => InstagInfluencerService;
  private removeId: number = null;

  public instagInfluencers: IInstagInfluencer[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllInstagInfluencers();
  }

  public clear(): void {
    this.retrieveAllInstagInfluencers();
  }

  public retrieveAllInstagInfluencers(): void {
    this.isFetching = true;

    this.instagInfluencerService()
      .retrieve()
      .then(
        res => {
          this.instagInfluencers = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IInstagInfluencer): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeInstagInfluencer(): void {
    this.instagInfluencerService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.instagInfluencer.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllInstagInfluencers();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
