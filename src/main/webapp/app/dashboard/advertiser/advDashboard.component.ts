import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import DiscussionThreads from '@/discussionThreads/discussionThreads.vue';
import SideNavBarComponent from '@/core/sideNavBar/sideNavBar.vue';
import SearchForm from '@/dashboard/partials/searchForm.vue';
import CampaignCard from '@/dashboard/partials/campaignCard.vue';
import { ICampaign } from '@/shared/model/campaign.model';
import CampaignService from '@/entities/campaign/campaign.service';
import CampaignUpdate from '@/entities/campaign/campaign-update.vue';

@Component({
  components: {
    'side-navbar': SideNavBarComponent,
    'search-form': SearchForm,
    'campaign-card': CampaignCard,
    'discussion-threads': DiscussionThreads,
    'campaign-update': CampaignUpdate,
  },
})
export default class AdvDashboard extends Vue {
  private currentComponent: string = 'search-form';

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

  public setCurrentComponent(component: string) {
    switch (component) {
      case 'search':
        this.currentComponent = 'search-form';
        return this.currentComponent;
      case 'discussion':
        this.currentComponent = 'discussion-threads';
        return this.currentComponent;
      case 'campaign':
        this.currentComponent = 'campaign-card';
        return this.currentComponent;
      default:
        break;
    }
  }
}
