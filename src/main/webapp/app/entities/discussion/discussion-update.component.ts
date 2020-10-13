import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import MessageService from '../message/message.service';
import { IMessage } from '@/shared/model/message.model';

import AlertService from '@/shared/alert/alert.service';
import { IDiscussion, Discussion } from '@/shared/model/discussion.model';
import DiscussionService from './discussion.service';

const validations: any = {
  discussion: {},
};

@Component({
  validations,
})
export default class DiscussionUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('discussionService') private discussionService: () => DiscussionService;
  public discussion: IDiscussion = new Discussion();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('messageService') private messageService: () => MessageService;

  public messages: IMessage[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.discussionId) {
        vm.retrieveDiscussion(to.params.discussionId);
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
    if (this.discussion.id) {
      this.discussionService()
        .update(this.discussion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.discussion.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.discussionService()
        .create(this.discussion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.discussion.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDiscussion(discussionId): void {
    this.discussionService()
      .find(discussionId)
      .then(res => {
        this.discussion = res;
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
    this.messageService()
      .retrieve()
      .then(res => {
        this.messages = res.data;
      });
  }
}
