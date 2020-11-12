/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InfluencerInfoComponent from '@/entities/influencer-info/influencer-info.vue';
import InfluencerInfoClass from '@/entities/influencer-info/influencer-info.component';
import InfluencerInfoService from '@/entities/influencer-info/influencer-info.service';

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
  describe('InfluencerInfo Management Component', () => {
    let wrapper: Wrapper<InfluencerInfoClass>;
    let comp: InfluencerInfoClass;
    let influencerInfoServiceStub: SinonStubbedInstance<InfluencerInfoService>;

    beforeEach(() => {
      influencerInfoServiceStub = sinon.createStubInstance<InfluencerInfoService>(InfluencerInfoService);
      influencerInfoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<InfluencerInfoClass>(InfluencerInfoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          influencerInfoService: () => influencerInfoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      influencerInfoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllInfluencerInfos();
      await comp.$nextTick();

      // THEN
      expect(influencerInfoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.influencerInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      influencerInfoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeInfluencerInfo();
      await comp.$nextTick();

      // THEN
      expect(influencerInfoServiceStub.delete.called).toBeTruthy();
      expect(influencerInfoServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
