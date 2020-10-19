import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IDiscussion } from '@/shared/model/discussion.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import DiscussionService from './discussion.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Discussion extends mixins(AlertMixin) {
  @Inject('discussionService') private discussionService: () => DiscussionService;
  private removeId: number = null;

  public discussions: IDiscussion[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllDiscussions();
  }

  public clear(): void {
    this.retrieveAllDiscussions();
  }

  public retrieveAllDiscussions(): void {
    this.isFetching = true;

    this.discussionService()
      .retrieve()
      .then(
        res => {
          this.discussions = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IDiscussion): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeDiscussion(): void {
    this.discussionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('influSuccessApp.discussion.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllDiscussions();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
