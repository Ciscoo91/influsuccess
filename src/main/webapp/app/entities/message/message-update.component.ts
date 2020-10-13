import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import UserService from '@/admin/user-management/user-management.service';

import DiscussionService from '../discussion/discussion.service';
import { IDiscussion } from '@/shared/model/discussion.model';

import AlertService from '@/shared/alert/alert.service';
import { IMessage, Message } from '@/shared/model/message.model';
import MessageService from './message.service';

const validations: any = {
  message: {
    status: {
      required,
    },
    content: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class MessageUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('messageService') private messageService: () => MessageService;
  public message: IMessage = new Message();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];

  @Inject('discussionService') private discussionService: () => DiscussionService;

  public discussions: IDiscussion[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.messageId) {
        vm.retrieveMessage(to.params.messageId);
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
    if (this.message.id) {
      this.messageService()
        .update(this.message)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.message.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.messageService()
        .create(this.message)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.message.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMessage(messageId): void {
    this.messageService()
      .find(messageId)
      .then(res => {
        this.message = res;
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
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
    this.discussionService()
      .retrieve()
      .then(res => {
        this.discussions = res.data;
      });
    this.discussionService()
      .retrieve()
      .then(res => {
        this.discussions = res.data;
      });
  }
}
