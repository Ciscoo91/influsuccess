import Component from 'vue-class-component';
import SearchFormService from '@/dashboard/partials/searchForm.service';
import { Inject, Vue } from 'vue-property-decorator';
import { influencerSearchDTO } from '@/shared/model/searchForm.model';

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
  public itemsPerPage: number[] = [6, 12, 22];
  public itemsPerPageSelected: number = this.itemsPerPage[0];
  public showResults: boolean = false;
  public isLoading: boolean = true;
  public results: any[] = [];
  public currentPage: number = 1;
  public totalCards: number = 0;
  public previousPage: number = 1;

  created() {
    this.getInfluencersPaegeable();
  }

  onSubmit() {
    this.getInfluencersPaegeable();
  }

  public getInfluencersPaegeable() {
    const requestBody = new influencerSearchDTO();
    requestBody.campaignCategoryEnum = '';
    requestBody.countryCode = '';
    requestBody.socialNetworkName = '';
    requestBody.username = '';
    requestBody.totalFollowersMin = 0;

    const pageable = new URLSearchParams({
      size: this.itemsPerPageSelected.toString(),
      page: (this.currentPage - 1).toString(),
    });

    this.searchFormService()
      .getInfluencersPaegeable(requestBody, pageable)
      .then(res => {
        console.log(res);
        this.isLoading = false;
        this.results = res.data.content;
        this.currentPage = res.data.pageable.pageNumber + 1;
        this.totalCards = res.data.totalElements;
        this.showResults = true;
      })
      .catch(err => console.error(err));
  }

  private getAllCountries() {
    this.searchFormService()
      .getAllCountries()
      .then(res => {
        const data = res;
        console.log(data);
      });
  }

  public getInfluencerById(id: number) {
    this.searchFormService()
      .getInfluencer(id)
      .then(res => console.log(res));
  }

  public getAllInfluencers() {
    this.searchFormService()
      .getAllInfluencers()
      .then(res => console.log(res.data));
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.getInfluencersPaegeable();
    }
  }

  public checkPage(event) {
    console.log(event);
  }
}
