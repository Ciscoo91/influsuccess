import { Component, Inject } from 'vue-property-decorator';
import { mixins } from 'vue-class-component';
import Vue2Filters from 'vue2-filters';
import UserManagementService from './user-management.service';
import AlertMixin from '@/shared/alert/alert.mixin';
import { UserFilter, MailUser } from '@/shared/model/user.model';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class JhiUserManagementComponent extends mixins(AlertMixin) {
  @Inject('userService') private userManagementService: () => UserManagementService;
  public error = '';
  public success = '';
  public users: any[] = [];
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public removeId: number = null;
  public isFetching = false;
  public optionsPerPage: any[] = [
    { value: 3, text: '3' },
    { value: 5, text: '5' },
    { value: 10, text: '10' },
    { value: 20, text: '20' },
  ];

  public currentPage = 1;
  public email = '';
  public firstName = '';
  public lastName = '';
  public login = '';

  public emailContent = '';
  public userToEmail: any = null;
  public messageResponse: any = null;

  public mounted(): void {
    this.loadAll();
  }

  public onSubmit() {
    this.retrieveFilteredAndPaginatedUsers();
  }

  public prepareUserForEmail(instance): void {
    this.userToEmail = instance;
  }

  public sendMail() {
    const mailUser: MailUser = new MailUser();
    mailUser.userEmail = this.userToEmail.email;
    mailUser.login = this.userToEmail.userLogin;
    mailUser.langKey = this.$store.getters.currentLanguage;
    mailUser.content = this.emailContent;

    this.userManagementService()
      .sendMail(mailUser)
      .then(
        res => {
          console.log(res);
          this.messageResponse = res.content;
        },
        err => {
          this.messageResponse = err.message;
        }
      );
  }

  public setActive(user, isActivated): void {
    user.activated = isActivated;
    this.userManagementService()
      .update(user)
      .then(() => {
        this.error = null;
        this.success = 'OK';
        this.loadAll();
      })
      .catch(() => {
        this.success = null;
        this.error = 'ERROR';
        user.activated = false;
      });
  }

  public loadAll(): void {
    this.userManagementService()
      .retrieve({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .then(res => {
        this.users = res.data;
        this.totalItems = Number(res.headers['x-total-count']);
        this.queryCount = this.totalItems;
      });
  }

  public sort(): any {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.loadAll();
  }

  public changeOrder(propOrder: string): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public deleteUser(): void {
    this.userManagementService()
      .remove(this.removeId)
      .then(res => {
        const message = this.$t(res.headers['x-influsuccessapp-alert'], {
          param: decodeURIComponent(res.headers['x-influsuccessapp-params'].replace(/\+/g, ' ')),
        });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.loadAll();
        this.closeDialog();
      });
  }

  public prepareRemove(instance): void {
    this.removeId = instance.login;
    if (<any>this.$refs.removeUser) {
      (<any>this.$refs.removeUser).show();
    }
  }

  public closeDialog(): void {
    (<any>this.$refs.removeUser).hide();
  }

  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }

  public retrieveFilteredAndPaginatedUsers(): void {
    this.isFetching = true;

    const userFilter: UserFilter = new UserFilter();
    userFilter.login = `${this.login}*`;
    userFilter.firstName = `${this.firstName}*`;
    userFilter.lastName = `${this.lastName}*`;
    userFilter.email = `${this.email}*`;

    const pageable = new URLSearchParams({
      page: (this.currentPage - 1).toString(),
      size: this.itemsPerPage.toString(),
    });

    this.userManagementService()
      .retriveFilteredAndPaginatedUsers(userFilter, pageable)
      .then(
        res => {
          this.users = res.content;
          this.totalItems = res.totalElements;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }
}
