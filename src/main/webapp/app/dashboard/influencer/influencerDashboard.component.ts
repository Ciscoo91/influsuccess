import Component from 'vue-class-component';
import {Inject, Vue} from "vue-property-decorator";
import {ICampaign} from "@/shared/model/campaign.model";
import CampaignService from "@/entities/campaign/campaign.service";
import CampaignUpdate from "@/entities/campaign/campaign-update.vue";
import DiscussionThreads from "@/discussionThreads/discussionThreads.vue";
import {IUser, ParticipantChat} from "@/shared/model/user.model";
import DiscussionService from "@/entities/discussion/discussion.service";
import {Message, MessageChat} from "@/shared/model/message.model";
import {Chat} from 'vue-quick-chat'
import 'vue-quick-chat/dist/vue-quick-chat.css';
import MessageService from "@/entities/message/message.service";
import {Discussion, IDiscussion} from "@/shared/model/discussion.model";

@Component({
  components:{
    'campaign-update': CampaignUpdate,
    'discussion-threads': DiscussionThreads,
    'Chat':Chat
  },
})
export default class InfluDashboard  extends Vue{

  public selectedCampaign: ICampaign = {};

  @Inject('campaignService')
  private campaignService: () => CampaignService;
  @Inject('discussionService')
  private discussionService: () => DiscussionService;
  @Inject('messageService')
  private messageService: () => MessageService;

  private campaigns :ICampaign[] = [];
  private isFetching: boolean = false;
  private fields :string[]= ['title','description','status','socialNetworks'];


  private participants: ParticipantChat[] = [];
  private messages: MessageChat[] = [];
  private myself : ParticipantChat =  new ParticipantChat();
  private placeholder: string = 'send your message';
  private selectedDiscussionId: number;
  private chatInterval: number;
  private discussionInterval: number;


  private colors = {
    header: {
      bg: '#d30303',
      text: '#fff'
    },
    message: {
      myself: {
        bg: '#fff',
        text: '#bdb8b8'
      },
      others: {
        bg: '#fb4141',
        text: '#fff'
      },
      messagesDisplay: {
        bg: '#f7f3f3'
      }
    },
    submitIcon: '#b91010',
    submitImageIcon: '#b91010',
  };
  private borderStyle = {
    topLeft: "10px",
    topRight: "10px",
    bottomLeft: "10px",
    bottomRight: "10px",
  };
  private hideCloseButton = false;
  private submitIconSize = 25;
  private closeButtonIconSize =  "20px";
  private timestampConfig = {
    format: 'yyyy-MM-dd HH:mm:ss',
    relative: false
  };
  private chatTitle: string = "";
  private campaignSelected: ICampaign;
private  currentUser: IUser ;
  private discussionThreadKeyRender: number = 0;

  created(): void {
  this.currentUser = this.$store.getters.account;
    this.retrieveOpenedCampaigns();
  }

  public retrieveOpenedCampaigns() :void{

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

  public initDiscussionThread(campaign: ICampaign){
    if (this.currentUser && campaign.userId) {
      this.discussionService().isAlreadyExistByParticipantAndCampaign(this.currentUser.id, campaign.id).then(
        res => {
          this.chatTitle = campaign.title;
          this.myself.id = this.currentUser.id;
          this.myself.name = this.currentUser.login;
          if (res) {
            this.participants = res.participants.filter(value => value.id !== this.myself.id && value.name!== this.myself.name);
            this.messages = res.messages;
            this.selectedDiscussionId = res.discussionId;
          }else{
            this.messages = [];
            this.campaignSelected = campaign;
            this.participants = new Array(new ParticipantChat(campaign.userId,campaign.userLogin))
          }
          this.$root.$emit('bv::show::modal', 'chatModalInflu')
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  public onMessageSubmit(message){
  if (this.selectedDiscussionId) {
    this.messageService().saveMessageChat(message, this.selectedDiscussionId).then(
      res =>{
        this.discussionThreadKeyRender++;
      }
    );
  }
  else{
    if(this.currentUser && this.campaignSelected) {
      const discussion: Discussion = new Discussion();
      discussion.campaign = this.campaignSelected;
      discussion.participantIds = new Array(this.currentUser.id, this.campaignSelected.userId);
      this.discussionService().save(discussion).then(
        res => {
          this.selectedDiscussionId = res.id;
          this.messageService().saveMessageChat(message, res.id).then(
            res => {
              this.discussionThreadKeyRender++;
            }
          )
        },
        err => {
          console.log(err)
        }
      )
    }
  }
  }

  public closeChat(){
    this.selectedDiscussionId = null;
  }

}
