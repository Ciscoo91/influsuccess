import { Component, Vue, Inject } from 'vue-property-decorator';

import { IContact } from '@/shared/model/contact.model';
import ContactService from './contact.service';

@Component
export default class ContactDetails extends Vue {
  @Inject('contactService') private contactService: () => ContactService;
  public contact: IContact = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactId) {
        vm.retrieveContact(to.params.contactId);
      }
    });
  }

  public retrieveContact(contactId) {
    this.contactService()
      .find(contactId)
      .then(res => {
        this.contact = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
