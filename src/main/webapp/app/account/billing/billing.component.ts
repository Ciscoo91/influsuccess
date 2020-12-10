import Vue from 'vue';
import { Inject } from 'vue-property-decorator';
import BillingService from './billing.service';

export default class Billing extends Vue {
  private priceId;

  @Inject('billingService') private billingService: () => BillingService;
  public createCheckoutSession = function (priceId) {
    return this.billingService()
      .createCheckoutSession(priceId)
      .then(res => res.json())
      .then(data => console.log(data));
  };
}
