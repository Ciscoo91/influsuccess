import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';
import { email, helpers, maxLength, minLength, required, sameAs, numeric, alpha } from 'vuelidate/lib/validators';
import LoginService from '@/account/login.service';
import RegisterService from '@/account/register/register.service';
import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from '@/constants';
import { VueTelInput } from 'vue-tel-input';
import { Authority } from '@/shared/security/authority';
import { UserExtra } from '@/shared/model/user-extra.model';
import moment from 'moment';
import { ICountry } from '@/shared/model/country.model';

const loginPattern = helpers.regex('alpha', /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/);
const validations: any = {
  role: {
    required,
  },
  registerAccount: {
    login: {
      required,
      minLength: minLength(1),
      maxLength: maxLength(50),
      pattern: loginPattern,
    },
    firstName: {
      required,
      minLength: minLength(1),
      alpha,
    },
    lastName: {
      required,
      minLength: minLength(1),
      alpha,
    },
    userExtra: {
      birthday: {
        required,
      },
      country: {
        required,
        minLength: minLength(1),
        alpha,
      },
      phone: {
        required,
        maxLength: maxLength(15),
      },
    },

    email: {
      required,
      minLength: minLength(5),
      maxLength: maxLength(254),
      email,
    },
    password: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(254),
    },
  },
  confirmPassword: {
    required,
    minLength: minLength(4),
    maxLength: maxLength(254),
    // prettier-ignore
    sameAsPassword: sameAs(function() {
        return this.registerAccount.password;
      })
  },
};

@Component({
  components: {
    'vue-tel-input': VueTelInput,
  },
  validations,
})
export default class Register extends Vue {
  @Inject('registerService') private registerService: () => RegisterService;
  @Inject('loginService') private loginService: () => LoginService;
  public roles: string[] = ['ROLE_INFLUENCER', 'ROLE_ADVERTISER'];
  public role: Authority = null;

  public registerAccount: any = {
    login: undefined,
    firstName: undefined,
    lastName: undefined,
    email: undefined,
    password: undefined,
    authorities: [],
    userExtra: new UserExtra(),
  };

  public confirmPassword: any = null;
  public error = '';
  public errorEmailExists = '';
  public errorUserExists = '';
  public success = false;
  public countries: ICountry[];

  public mounted(): void {
    this.registerService()
      .getCountries()
      .then(
        res => {
          this.countries = res;
        },
        err => {}
      );
  }

  public register(): void {
    this.error = null;
    this.errorUserExists = null;
    this.errorEmailExists = null;
    this.registerAccount.langKey = this.$store.getters.currentLanguage;
    this.registerAccount.authorities.push(this.role);
    this.registerAccount.userExtra.birthday = moment().toISOString(this.registerAccount.userExtra.birthday);
    this.registerService()
      .processRegistration(this.registerAccount)
      .then(res => {
        this.success = true;
      })
      .catch(error => {
        this.success = null;
        if (error.response.status === 400 && error.response.data.type === LOGIN_ALREADY_USED_TYPE) {
          this.errorUserExists = 'ERROR';
        } else if (error.response.status === 400 && error.response.data.type === EMAIL_ALREADY_USED_TYPE) {
          this.errorEmailExists = 'ERROR';
        } else {
          this.error = 'ERROR';
        }
      });
  }

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public onSelect(event) {
    console.log(this.$v);
    console.log(this.$v.role.$invalid);
    console.log(this.$v.registerAccount.userExtra.phone.$invalid);
  }
}
