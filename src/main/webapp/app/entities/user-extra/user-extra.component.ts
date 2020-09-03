import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserExtra } from '@/shared/model/user-extra.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import UserExtraService from './user-extra.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UserExtra extends mixins(AlertMixin) {
  @Inject('userExtraService') private userExtraService: () => UserExtraService;
  private removeId: number = null;

  public userExtras: IUserExtra[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserExtras();
  }

  public clear(): void {
    this.retrieveAllUserExtras();
  }

  public retrieveAllUserExtras(): void {
    this.isFetching = true;

    this.userExtraService()
      .retrieve()
      .then(
        res => {
          this.userExtras = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IUserExtra): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserExtra(): void {
    this.userExtraService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.userExtra.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllUserExtras();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
