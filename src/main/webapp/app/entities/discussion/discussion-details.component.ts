import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDiscussion } from '@/shared/model/discussion.model';
import DiscussionService from './discussion.service';

@Component
export default class DiscussionDetails extends Vue {
  @Inject('discussionService') private discussionService: () => DiscussionService;
  public discussion: IDiscussion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.discussionId) {
        vm.retrieveDiscussion(to.params.discussionId);
      }
    });
  }

  public retrieveDiscussion(discussionId) {
    this.discussionService()
      .find(discussionId)
      .then(res => {
        this.discussion = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
