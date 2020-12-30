import Vue from 'vue';

export default class FirstStepForm extends Vue {
  created() {
    console.log('first component created..');
  }
  onClickButton() {
    // this.$emit("changeBillingComponent");
    console.log(this);
  }
}
