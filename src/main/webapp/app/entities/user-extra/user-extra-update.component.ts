import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import UserService from '@/admin/user-management/user-management.service';

import AlertService from '@/shared/alert/alert.service';
import { IUserExtra, UserExtra } from '@/shared/model/user-extra.model';
import UserExtraService from './user-extra.service';

const validations: any = {
  userExtra: {
    country: {
      required,
    },
    birthday: {
      required,
    },
    phone: {},
  },
};

@Component({
  validations,
})
export default class UserExtraUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('userExtraService') private userExtraService: () => UserExtraService;
  public userExtra: IUserExtra = new UserExtra();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userExtraId) {
        vm.retrieveUserExtra(to.params.userExtraId);
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
    if (this.userExtra.id) {
      this.userExtraService()
        .update(this.userExtra)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.userExtra.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.userExtraService()
        .create(this.userExtra)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.userExtra.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUserExtra(userExtraId): void {
    this.userExtraService()
      .find(userExtraId)
      .then(res => {
        this.userExtra = res;
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
  }
}
