import Vue from 'vue';
import { Inject, Component } from 'vue-property-decorator';
import BillingService from '@/account/billing/billing.service';
import { StripeCheckout } from '@vue-stripe/vue-stripe/dist';

@Component({
  components: {
    'stripe-checkout': StripeCheckout,
  },
})
export default class Billing extends Vue {
  @Inject('billingService') private billingService: () => BillingService;

  public priceId: string = '';
  public currentComponent = 'firstStep';
  public isFirstStep: boolean = true;

  public publishableKey: string =
    'pk_test_51Hw9O7DWZJJd9ekJ5JZPEGFWDeLx70lulEkBdhpxjcNigd2uxw3PFs4PHOXmTaO1ZmA04DtWFpbc1B5J7ridHa3u00FGGbiWxw';
  public successUrl: string = 'http://localhost:9000/account/successBilling';
  public cancelUrl: string = 'http://localhost:9000/account/cancelBilling';
  public loading: boolean = false;
  public lineItems: any[] = [
    {
      price: 'price_1HwP2iDWZJJd9ekJjm17tt8u',
      quantity: 1,
    },
    {
      price: 'price_1HwPVBDWZJJd9ekJHHAZtBYN',
      quantity: 1,
    },
  ];

  computed() {
    return this.currentComponent;
  }

  public setPriceId = event => {
    console.log(this.priceId);
    console.log(this.billingService());
  };

  public createCheckoutSession(priceId) {
    this.billingService()
      .createCheckoutSession(priceId)
      .then(res => res.json())
      .then(data => console.log(data));
  }

  public submit() {
    (this.$refs.checkoutRef as any).redirectToCheckout();
  }
}
