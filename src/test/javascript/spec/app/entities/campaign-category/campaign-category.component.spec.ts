/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CampaignCategoryComponent from '@/entities/campaign-category/campaign-category.vue';
import CampaignCategoryClass from '@/entities/campaign-category/campaign-category.component';
import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';

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
  describe('CampaignCategory Management Component', () => {
    let wrapper: Wrapper<CampaignCategoryClass>;
    let comp: CampaignCategoryClass;
    let campaignCategoryServiceStub: SinonStubbedInstance<CampaignCategoryService>;

    beforeEach(() => {
      campaignCategoryServiceStub = sinon.createStubInstance<CampaignCategoryService>(CampaignCategoryService);
      campaignCategoryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CampaignCategoryClass>(CampaignCategoryComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          campaignCategoryService: () => campaignCategoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      campaignCategoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCampaignCategorys();
      await comp.$nextTick();

      // THEN
      expect(campaignCategoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.campaignCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      campaignCategoryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCampaignCategory();
      await comp.$nextTick();

      // THEN
      expect(campaignCategoryServiceStub.delete.called).toBeTruthy();
      expect(campaignCategoryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
