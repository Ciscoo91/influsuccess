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
  public isFetching = false;
  public fields: string[] = ['title', 'description', 'status', 'socialNetworks'];
  public currentPage = 1;
  public optionsPerPage: any[] = [
    { value: 3, text: '3' },
    { value: 5, text: '5' },
    { value: 10, text: '10' },
    { value: 20, text: '20' },
  ];
  public perPage = 3;
  public title = '';
  public userLogin = '';
  public status: CampaignStatus = CampaignStatus.OPENED;
  public sort = `id,${Sort.ASC}`;

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

    const campaignFilter: CampaignFilter = new CampaignFilter();
    campaignFilter.status = `${this.status}*`;
    campaignFilter.userLogin = `${this.userLogin}*`;
    campaignFilter.title = `${this.title}*`;

    const pageable = new URLSearchParams({
      page: (this.currentPage - 1).toString(),
      size: this.perPage.toString(),
    });

    this.campaignService()
      .retrievePaginatedCampaigns(campaignFilter, pageable)
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
