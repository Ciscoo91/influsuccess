/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CampaignComponent from '@/entities/campaign/campaign.vue';
import CampaignClass from '@/entities/campaign/campaign.component';
import CampaignService from '@/entities/campaign/campaign.service';

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
  describe('Campaign Management Component', () => {
    let wrapper: Wrapper<CampaignClass>;
    let comp: CampaignClass;
    let campaignServiceStub: SinonStubbedInstance<CampaignService>;

    beforeEach(() => {
      campaignServiceStub = sinon.createStubInstance<CampaignService>(CampaignService);
      campaignServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CampaignClass>(CampaignComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          campaignService: () => campaignServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      campaignServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCampaigns();
      await comp.$nextTick();

      // THEN
      expect(campaignServiceStub.retrieve.called).toBeTruthy();
      expect(comp.campaigns[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      campaignServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCampaign();
      await comp.$nextTick();

      // THEN
      expect(campaignServiceStub.delete.called).toBeTruthy();
      expect(campaignServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
