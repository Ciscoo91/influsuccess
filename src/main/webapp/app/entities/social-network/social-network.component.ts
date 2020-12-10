import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISocialNetwork } from '@/shared/model/social-network.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SocialNetworkService from './social-network.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SocialNetwork extends mixins(AlertMixin) {
  @Inject('socialNetworkService') private socialNetworkService: () => SocialNetworkService;
  private removeId: number = null;

  public socialNetworks: ISocialNetwork[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSocialNetworks();
  }

  public clear(): void {
    this.retrieveAllSocialNetworks();
  }

  public retrieveAllSocialNetworks(): void {
    this.isFetching = true;

    this.socialNetworkService()
      .retrieve()
      .then(
        res => {
          this.socialNetworks = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISocialNetwork): void {
    this.removeId = instance.name;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSocialNetwork(): void {
    this.socialNetworkService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.socialNetwork.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllSocialNetworks();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
