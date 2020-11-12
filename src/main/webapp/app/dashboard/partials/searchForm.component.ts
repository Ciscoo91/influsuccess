import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';

@Component({})
export default class SearchForm extends Vue {
  public showResults = false;

  public results: any[] = [
    {
      id: 1,
      name: 'John Doe',
      followers: 12300,
      socialMedias: ['instagram', 'facebook', 'twitter'],
      image: 'https://source.unsplash.com/MTZTGvDsHFY/100X200',
    },
    {
      id: 2,
      name: 'Jone Smith',
      followers: 300000,
      socialMedias: ['instagram', 'facebook', 'snapchat'],
      image: 'https://source.unsplash.com/mEZ3PoFGs_k/100X200',
    },
    {
      id: 3,
      name: 'Earl Thomas',
      followers: 17800,
      socialMedias: ['instagram', 'facebook', 'snapchat'],
      image: 'https://source.unsplash.com/OhKElOkQ3RE/100X200',
    },
  ];

  onSubmit(event) {
    // request to api
    this.showResults = true;
  }
}
