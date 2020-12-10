import axios from 'axios';
import { ICountry } from '@/shared/model/country.model';

export default class RegisterService {
  public processRegistration(account: any): Promise<any> {
    return axios.post('api/register', account);
  }

  public getCountries(): Promise<ICountry[]> {
    return new Promise<ICountry[]>((resolve, reject) => {
      axios
        .get('api/countries')
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
