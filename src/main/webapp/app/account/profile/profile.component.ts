import { Vue } from 'vue-property-decorator';

export default class Settings extends Vue {
  public get username(): string {
    return this.$store.getters.account ? this.$store.getters.account.login : '';
  }
}
