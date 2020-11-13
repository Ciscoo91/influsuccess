import { Inject, Vue } from 'vue-property-decorator';
import Component from 'vue-class-component';
import { IDiscussionThreads } from '@/shared/model/discussionThreads.model';
import DiscussionService from '@/entities/discussion/discussion.service';
import MessageService from '@/entities/message/message.service';
import { Chat } from 'vue-quick-chat';
import 'vue-quick-chat/dist/vue-quick-chat.css';
import { ParticipantChat } from '@/shared/model/user.model';
import { MessageChat } from '@/shared/model/message.model';

@Component({
  components: {
    'Chat': Chat,
  },
})
export default class DiscussionThreads extends Vue {
  @Inject('discussionService')
  private discussionService: () => DiscussionService;

  @Inject('messageService')
  private messageService: () => MessageService;

  private discussions: IDiscussionThreads[] = [];
  private countNewMessages: number;
  private isFetching = false;
  private participants: ParticipantChat[] = [];
  private messages: MessageChat[] = [];
  private myself: ParticipantChat = new ParticipantChat();
  private placeholder = 'send your message';
  private selectedDiscussionId: number;
  private chatInterval: number;
  private discussionInterval: number;

  private colors = {
    header: {
      bg: '#d30303',
      text: '#fff',
    },
    message: {
      myself: {
        bg: '#fff',
        text: '#bdb8b8',
      },
      others: {
        bg: '#fb4141',
        text: '#fff',
      },
      messagesDisplay: {
        bg: '#f7f3f3',
      },
    },
    submitIcon: '#b91010',
    submitImageIcon: '#b91010',
  };
  private borderStyle = {
    topLeft: '10px',
    topRight: '10px',
    bottomLeft: '10px',
    bottomRight: '10px',
  };
  private hideCloseButton = false;
  private submitIconSize = 25;
  private closeButtonIconSize = '20px';
  private timestampConfig = {
    format: 'yyyy-MM-dd HH:mm:ss',
    relative: false,
  };
  private chatTitle = '';

  mounted(): void {
    this.retrievesDiscussions();
  }

  private retrievesDiscussions() {
    this.isFetching = true;
    const userId: number = this.$store.getters.account.id;
    if (userId) {
      this.discussionService()
        .retrieveDiscussionByParticipant(userId)
        .then(
          res => {
            this.discussions = res;
            this.isFetching = false;
          },
          err => {
            this.isFetching = false;
          }
        );
    }
  }

  public openChat(discussion: IDiscussionThreads) {
    window.clearInterval(this.discussionInterval);
    this.chatTitle = discussion.campaignTitle;
    this.retrievesChatDiscussion(discussion.discussionId);
    this.chatInterval = window.setInterval(() => {
      this.retrievesChatDiscussion(this.selectedDiscussionId);
    }, 5000);
  }

  public onMessageSubmit(message) {
    this.messageService().saveMessageChat(message, this.selectedDiscussionId);
  }

  public closeChat() {
    window.clearInterval(this.chatInterval);
    this.retrievesDiscussions();
    this.discussionInterval = window.setInterval(() => {
      this.retrievesDiscussions();
    }, 10000);
  }

  private retrievesChatDiscussion(discussionId: number) {
    this.selectedDiscussionId = discussionId;
    this.discussionService()
      .retrieveChatDiscussion(discussionId, this.$store.getters.account.id)
      .then(
        res => {
          if (res) {
            this.myself.id = this.$store.getters.account.id;
            this.myself.name = this.$store.getters.account.firstName;
            this.participants = res.participants.filter(value => value.id !== this.myself.id && value.name !== this.myself.name);
            this.messages = res.messages;
            this.$root.$emit('bv::show::modal', 'chatModal');
          }
        },
        err => {}
      );
  }
}
