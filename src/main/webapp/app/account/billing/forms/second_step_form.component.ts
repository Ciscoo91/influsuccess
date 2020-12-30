import { Vue } from 'vue-property-decorator';

export default class SecondStepForm extends Vue {
  created() {
    console.log('second step form created..');
  }
  onClickButton() {
    console.log(this);
    // this.$emit("changeBillingComponent");
  }
}
