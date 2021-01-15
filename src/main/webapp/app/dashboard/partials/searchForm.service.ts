import axios from 'axios';

export default class SearchFormService {
  public getAllInfluencers(): Promise<any> {
    return new Promise((resolve, reject) => {
      axios
        .get('api/influencers/')
        .then(res => resolve(res))
        .catch(err => reject(err));
    });
  }

  public getInfluencer(id: number): Promise<any> {
    return new Promise((resolve, reject) => {
      axios
        .get(`api/influencers/${id}`)
        .then(res => resolve(res))
        .catch(err => reject(err));
    });
  }

  public getInfluencersPaegeable(influencerSearchDTO, params): Promise<any> {
    return new Promise((resolve, reject) => {
      axios
        .post(`api/influencers/page?${params}`, influencerSearchDTO)
        .then(res => resolve(res))
        .catch(err => reject(err));
    });
  }

  public deleteInfluencer(id: number): Promise<any> {
    return new Promise((resolve, reject) => {
      axios
        .delete(`api/influencers/${id}`)
        .then(res => resolve(res))
        .catch(err => reject(err));
    });
  }

  public getAllCountries(): Promise<any> {
    return new Promise((resolve, reject) => {
      axios
        .get('api/countries')
        .then(res => resolve(res.data))
        .catch(err => reject(err));
    });
  }
}
