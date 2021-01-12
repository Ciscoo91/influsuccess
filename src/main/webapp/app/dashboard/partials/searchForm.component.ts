import Component from 'vue-class-component';
import SearchFormService from '@/dashboard/partials/searchForm.service';
import { Inject, Vue } from 'vue-property-decorator';

@Component({})
export default class SearchForm extends Vue {
  @Inject('searchFormService')
  private searchFormService: () => SearchFormService;
  public username: string = '';
  public countries: [] = [];
  public countrySelected: string = '';
  public numberofFollowers: number = 10;
  public platforms: string[] = ['Instagram', 'Facebook', 'LinkedIn', 'Pinterest', 'Snapchat', 'Tiktok', 'Twitter', 'Youtube'];
  public paltformSelected: string = '';
  public categories: any[] = ['healthy', 'workout', 'vegan'];
  public categorySelected: string = '';
  public itemsPerPage: number[] = [5, 10, 20];
  public itemsPerPageSelected: number = 5;
  public showResults: boolean = false;

  created() {
    this.getAllCountries();
  }

  public results: any[] = [
    {
      id: 1,
      name: 'John Doe',
      followers: 12300,
      socialMedias: ['instagram', 'facebook', 'twitter'],
      image: 'https://source.unsplash.com/MTZTGvDsHFY/100X200',
      country: 'France',
      categories: ['sport', 'lifestyle', 'food'],
    },
    {
      id: 2,
      name: 'Jane Smith',
      followers: 300000,
      socialMedias: ['instagram', 'facebook', 'snapchat'],
      image: 'https://source.unsplash.com/mEZ3PoFGs_k/100X200',
      country: 'England',
      categories: ['sport', 'lifestyle', 'food'],
    },
    {
      id: 3,
      name: 'Earl Thomas',
      followers: 17800,
      socialMedias: ['instagram', 'facebook', 'snapchat'],
      image: 'https://source.unsplash.com/OhKElOkQ3RE/100X200',
      country: 'China',
      categories: ['sport', 'lifestyle', 'food'],
    },
  ];

  private getAllCountries() {
    this.searchFormService()
      .getAllCountries()
      .then(res => {
        const data = res;
        console.log(data);
      });
  }
}
