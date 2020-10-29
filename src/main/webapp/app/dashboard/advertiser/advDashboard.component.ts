import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import DiscussionThreads from '@/discussionThreads/discussionThreads.vue';
import SideNavBarComponent from '@/core/sideNavBar/sideNavBar.vue';
import SearchForm from '@/dashboard/partials/searchForm.vue';
import CampaignCard from '@/dashboard/partials/campaignCard.vue';

@Component({
  components: {
    'side-navbar': SideNavBarComponent,
    'search-form': SearchForm,
    'campaign-card': CampaignCard,
    'discussion-threads': DiscussionThreads,
  },
})
export default class AdvDashboard extends Vue {
  private currentComponent: string = 'search-form';

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
