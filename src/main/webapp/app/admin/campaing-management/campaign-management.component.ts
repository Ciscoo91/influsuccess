import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import { ICampaign, CampaignFilter, CampaignStatus, Sort } from '@/shared/model/campaign.model';
import CampaignService from '@/entities/campaign/campaign.service';
import CampaignUpdate from '@/entities/campaign/campaign-update.vue';

@Component({
  components: {
    'campaign-update': CampaignUpdate,
  },
})
export default class CampaignManagementComponent extends Vue {
  public selectedCampaign: ICampaign = {};

  @Inject('campaignService')
  private campaignService: () => CampaignService;

  public campaigns: ICampaign[] = [];
  public isFetching: boolean = false;
  public fields: string[] = ['title', 'description', 'status', 'socialNetworks'];
  public currentPage: number = 1;
  public optionsPerPage: any[] = [
    { value: 3, text: '3' },
    { value: 5, text: '5' },
    { value: 10, text: '10' },
    { value: 20, text: '20' },
  ];
  public perPage = 3;
  public title: string = '';
  public userLogin: string = '';
  public status: CampaignStatus = CampaignStatus.OPENED;
  public sort: Sort = Sort.ASC;

  public campaignPageable = null;
  private campaignSelected: ICampaign;
  public rows = 0;

  created(): void {
    this.retrievePaginatedCampaigns();
  }

  onSubmit() {
    this.retrievePaginatedCampaigns();
  }

  public retrieveOpenedCampaigns(): void {
    this.isFetching = true;

    this.campaignService()
      .retrieveOpenedCampaigns()
      .then(
        res => {
          this.campaigns = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public retrievePaginatedCampaigns(): void {
    this.isFetching = true;

    const requestBody = { title: this.title, status: this.status, userLogin: this.userLogin };

    const pageable = new URLSearchParams({
      page: (this.currentPage - 1).toString(),
      size: this.perPage.toString(),
      sort: this.sort,
    });

    this.campaignService()
      .retrievePaginatedCampaigns(requestBody, pageable)
      .then(
        res => {
          this.campaigns = res.content;
          this.rows = res.totalElements;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }
  public pageChange(value) {
    this.currentPage = value;
    this.retrievePaginatedCampaigns();
  }
}
