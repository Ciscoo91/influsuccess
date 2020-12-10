import MessageService from '@/entities/message/message.service';
import { Component, Inject, Prop, Vue } from 'vue-property-decorator';

@Component
export default class SideNavBarComponent extends Vue {
  public totalNewMessageCount = 0;
  public menuItems: any[] = ['search', 'campaigns', 'discussions'];

  @Inject('messageService')
  private messageService: () => MessageService;

  public mounted(): void {
    this.getAllNewMessageCount();

    this.$root.$on('chatOpened', () => {
      this.getAllNewMessageCount();
    });
  }

  private getAllNewMessageCount() {
    this.messageService()
      .getAllNewMessageCount(this.$store.getters.account.id)
      .then(
        res => {
          this.totalNewMessageCount = res;
        },
        err => {}
      );
  }

  onClickItem(item) {
    this.$emit('currentComponent', item);
  }
}
