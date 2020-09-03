import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import CampaignService from '../campaign/campaign.service';
import { ICampaign } from '@/shared/model/campaign.model';

import InstagInfluencerService from '../instag-influencer/instag-influencer.service';
import { IInstagInfluencer } from '@/shared/model/instag-influencer.model';

import AlertService from '@/shared/alert/alert.service';
import { IContact, Contact } from '@/shared/model/contact.model';
import ContactService from './contact.service';

const validations: any = {
  contact: {},
};

@Component({
  validations,
})
export default class ContactUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contactService') private contactService: () => ContactService;
  public contact: IContact = new Contact();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('campaignService') private campaignService: () => CampaignService;

  public campaigns: ICampaign[] = [];

  @Inject('instagInfluencerService') private instagInfluencerService: () => InstagInfluencerService;

  public instagInfluencers: IInstagInfluencer[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactId) {
        vm.retrieveContact(to.params.contactId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.contact.id) {
      this.contactService()
        .update(this.contact)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.contact.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contactService()
        .create(this.contact)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.contact.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContact(contactId): void {
    this.contactService()
      .find(contactId)
      .then(res => {
        this.contact = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.campaignService()
      .retrieve()
      .then(res => {
        this.campaigns = res.data;
      });
    this.instagInfluencerService()
      .retrieve()
      .then(res => {
        this.instagInfluencers = res.data;
      });
  }
}
