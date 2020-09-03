/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InstagInfluencerComponent from '@/entities/instag-influencer/instag-influencer.vue';
import InstagInfluencerClass from '@/entities/instag-influencer/instag-influencer.component';
import InstagInfluencerService from '@/entities/instag-influencer/instag-influencer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('InstagInfluencer Management Component', () => {
    let wrapper: Wrapper<InstagInfluencerClass>;
    let comp: InstagInfluencerClass;
    let instagInfluencerServiceStub: SinonStubbedInstance<InstagInfluencerService>;

    beforeEach(() => {
      instagInfluencerServiceStub = sinon.createStubInstance<InstagInfluencerService>(InstagInfluencerService);
      instagInfluencerServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<InstagInfluencerClass>(InstagInfluencerComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          instagInfluencerService: () => instagInfluencerServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      instagInfluencerServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllInstagInfluencers();
      await comp.$nextTick();

      // THEN
      expect(instagInfluencerServiceStub.retrieve.called).toBeTruthy();
      expect(comp.instagInfluencers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      instagInfluencerServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeInstagInfluencer();
      await comp.$nextTick();

      // THEN
      expect(instagInfluencerServiceStub.delete.called).toBeTruthy();
      expect(instagInfluencerServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
