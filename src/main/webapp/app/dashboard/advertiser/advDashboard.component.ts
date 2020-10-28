import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import { ICampaign } from '@/shared/model/campaign.model';
import CampaignService from '@/entities/campaign/campaign.service';
import CampaignUpdate from '@/entities/campaign/campaign-update.vue';
import DiscussionThreads from '@/discussionThreads/discussionThreads.vue';

@Component({
  components: {
    'campaign-update': CampaignUpdate,
    'discussion-threads': DiscussionThreads,
  },
})
export default class AdvDashboard extends Vue {
  public selectedCampaign: ICampaign = {};

  @Inject('campaignService')
  private campaignService: () => CampaignService;

  private campaigns: ICampaign[] = [];
  private isFetching: boolean = false;
  private rowIsSelected: boolean = false;
  private fields: string[] = ['title', 'description', 'status', 'socialNetworks'];

  created(): void {
    this.retrieveOpenedCampaigns();
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

  public onRowSelected(items): void {
    if (Object.keys(items).length && Object.keys(items).length >= 0) {
      this.rowIsSelected = true;
      this.selectedCampaign = items[0];
    } else {
      this.rowIsSelected = false;
      this.selectedCampaign = {};
    }
  }

  public removeCampaign(): void {
    this.campaignService()
      .delete(this.selectedCampaign.id)
      .then(() => {
        this.retrieveOpenedCampaigns();
        this.selectedCampaign = {};
        this.closeDialogue('removeCampaign');
      });
  }

  public closeDialogue(idModal: string): void {
    this.$root.$emit('bv::hide::modal', idModal);
  }
}
