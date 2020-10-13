import {Inject, Vue} from "vue-property-decorator";
import Component from 'vue-class-component';
import {IDiscussionThreads} from "@/shared/model/discussionThreads.model";
import DiscussionService from "@/entities/discussion/discussion.service";
import MessageService from "@/entities/message/message.service";
import {Chat} from 'vue-quick-chat'
import {ParticipantChat} from "@/shared/model/user.model";
import {MessageChat} from "@/shared/model/message.model";

@Component({
  components: {
    'Chat': Chat
  }
})
export default class DiscussionThreads extends Vue {
  @Inject('discussionService')
  private discussionService: () => DiscussionService;

  @Inject('messageService')
  private messageService: () => MessageService;

  private discussions: IDiscussionThreads[] = [];
  private countNewMessages: number;
  private isFetching: boolean = false;
  private participants: ParticipantChat[] = [];
  private  messages: MessageChat[] = [];
  private placeholder = 'send your message';

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
  hideCloseButton = false;
  submitIconSize = 25;
  closeButtonIconSize =  "20px";


  mounted(): void {
    this.retrievesDiscussions();
  }

  private retrievesDiscussions() {
    this.isFetching = true;
    const userId: number = this.$store.getters.account.id;
    if (userId) {
      this.discussionService().retrieveDiscussionByParticipant(userId)
        .then(
          res => {
            this.discussions = res;
            this.isFetching = false;

          },
          err => {
            this.isFetching = false;
          }
        )
    }
  }

  public openChat(discussionId: number) {
    this.$root.$emit('bv::show::modal', 'chatModal')
  }

  public onType (event){
    //here you can set any behavior
  }
  public onMessageSubmit(message){
    //here you can set any behavior
  }
}
