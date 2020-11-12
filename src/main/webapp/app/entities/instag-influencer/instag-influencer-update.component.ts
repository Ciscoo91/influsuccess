import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IInstagInfluencer, InstagInfluencer } from '@/shared/model/instag-influencer.model';
import InstagInfluencerService from './instag-influencer.service';

const validations: any = {
  instagInfluencer: {
    url: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class InstagInfluencerUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('instagInfluencerService') private instagInfluencerService: () => InstagInfluencerService;
  public instagInfluencer: IInstagInfluencer = new InstagInfluencer();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.instagInfluencerId) {
        vm.retrieveInstagInfluencer(to.params.instagInfluencerId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.instagInfluencer.id) {
      this.instagInfluencerService()
        .update(this.instagInfluencer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.instagInfluencer.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.instagInfluencerService()
        .create(this.instagInfluencer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('influSuccessApp.instagInfluencer.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInstagInfluencer(instagInfluencerId): void {
    this.instagInfluencerService()
      .find(instagInfluencerId)
      .then(res => {
        this.instagInfluencer = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
