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
export default class CampaignCard extends Vue {
  public selectedCampaign: ICampaign = {};

  @Inject('campaignService')
  private campaignService: () => CampaignService;

  private campaigns: ICampaign[] = [];
  private isFetching: boolean = false;

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
