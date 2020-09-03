import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IContact } from '@/shared/model/contact.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ContactService from './contact.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Contact extends mixins(AlertMixin) {
  @Inject('contactService') private contactService: () => ContactService;
  private removeId: number = null;

  public contacts: IContact[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllContacts();
  }

  public clear(): void {
    this.retrieveAllContacts();
  }

  public retrieveAllContacts(): void {
    this.isFetching = true;

    this.contactService()
      .retrieve()
      .then(
        res => {
          this.contacts = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IContact): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeContact(): void {
    this.contactService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.contact.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllContacts();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
