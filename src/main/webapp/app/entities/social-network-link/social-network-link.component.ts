import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SocialNetworkLinkService from './social-network-link.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SocialNetworkLink extends mixins(AlertMixin) {
  @Inject('socialNetworkLinkService') private socialNetworkLinkService: () => SocialNetworkLinkService;
  private removeId: number = null;

  public socialNetworkLinks: ISocialNetworkLink[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSocialNetworkLinks();
  }

  public clear(): void {
    this.retrieveAllSocialNetworkLinks();
  }

  public retrieveAllSocialNetworkLinks(): void {
    this.isFetching = true;

    this.socialNetworkLinkService()
      .retrieve()
      .then(
        res => {
          this.socialNetworkLinks = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISocialNetworkLink): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSocialNetworkLink(): void {
    this.socialNetworkLinkService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.socialNetworkLink.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllSocialNetworkLinks();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
