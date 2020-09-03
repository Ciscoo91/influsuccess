import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUserExtra } from '@/shared/model/user-extra.model';
import UserExtraService from './user-extra.service';

@Component
export default class UserExtraDetails extends Vue {
  @Inject('userExtraService') private userExtraService: () => UserExtraService;
  public userExtra: IUserExtra = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userExtraId) {
        vm.retrieveUserExtra(to.params.userExtraId);
      }
    });
  }

  public retrieveUserExtra(userExtraId) {
    this.userExtraService()
      .find(userExtraId)
      .then(res => {
        this.userExtra = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
