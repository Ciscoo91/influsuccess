import axios from 'axios';

export default class BillingService {
  public createCheckoutSession(requestBody): Promise<any> {
    return new Promise((resolve, reject) => {
      axios.post('api/create-checkout-session', requestBody).then(res => resolve(res));
    });
  }
}