import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import { ICampaign } from '@/shared/model/campaign.model';
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
  public filters: any = {
    id: '',
    user: '',
    status: '',
    target: '',
    categories: '',
  };

  private campaignSelected: ICampaign;

  created(): void {
    this.retrieveCampaigns();
  }

  computed() {}

  public retrieveCampaigns(): void {
    this.isFetching = true;
    this.campaignService()
      .retrieve()
      .then(
        res => {
          console.log(res.data[0]);
          this.campaigns = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
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
}
