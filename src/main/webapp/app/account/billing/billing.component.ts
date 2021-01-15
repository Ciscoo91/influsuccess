import Vue from 'vue';
import { Inject } from 'vue-property-decorator';
import BillingService from '@/account/billing/billing.service';

export default class Billing extends Vue {
  public priceId: string = '';
  public step: number = 0;
  public prevButton: boolean = false;

  @Inject('billingService') private billingService: () => BillingService;

  computed() {
    return this.step;
  }

  public setPriceId = event => {
    console.log(this.priceId);
    console.log(this.billingService());
  };

  public nextStep = () => {
    this.step++;
    console.log(this.step);
    if (this.step > 0) {
      this.prevButton = true;
    }
  };

  public prevStep = () => {
    this.step--;
    console.log(this.step);
  };

  public createCheckoutSession(priceId) {
    this.billingService()
      .createCheckoutSession(priceId)
      .then(res => res.json())
      .then(data => console.log(data));
  }
}
