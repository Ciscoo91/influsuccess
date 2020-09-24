import Component from 'vue-class-component';
import {Inject, Vue} from "vue-property-decorator";
import {ICampaign} from "@/shared/model/campaign.model";
import CampaignService from "@/entities/campaign/campaign.service";

@Component({
  components:{
  }
})
export default class AdvDashboardComponent extends Vue{
  @Inject('campaignService')
  private campaignService: () => CampaignService;

  private campaigns :ICampaign[] = [];
  private isFetching: boolean = false;
  private rowIsSelected: boolean = false;
  private fields :string[]= ['Title','User'];

  mounted(): void {
    this.retrieveCampaigns();
  }

  public retrieveCampaigns() :void{

    this.isFetching = true;

    this.campaignService()
      .retrieve()
      .then(
        res => {
          this.campaigns = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public onRowSelected(items):void{
    if(items.lenght && items.lenght === 0){
      this.rowIsSelected = false;
    }
    this.rowIsSelected = !this.rowIsSelected;
  }
}
