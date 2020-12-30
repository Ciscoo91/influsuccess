import Vue from 'vue';
import { Inject, Component } from 'vue-property-decorator';
import BillingService from '@/account/billing/billing.service';
import FirstStepForm from '@/account/billing/forms/first_step_form.vue';
import SecondStepForm from '@/account/billing/forms/second_step_form.vue';

@Component({
  components: {
    firstStep: FirstStepForm,
    secondStep: SecondStepForm,
  },
})
export default class Billing extends Vue {
  public priceId: string = '';
  public currentComponent = 'firstStep';
  public isFirstStep: boolean = true;

  @Inject('billingService') private billingService: () => BillingService;

  computed() {
    return this.currentComponent;
  }

  public setPriceId = event => {
    console.log(this.priceId);
    console.log(this.billingService());
  };

  onChangeStep() {
    this.isFirstStep = !this.isFirstStep;
    if (this.isFirstStep) {
      this.currentComponent = 'firstStep';
    } else {
      this.currentComponent = 'secondStep';
    }
  }

  public createCheckoutSession(priceId) {
    this.billingService()
      .createCheckoutSession(priceId)
      .then(res => res.json())
      .then(data => console.log(data));
  }
}
