/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CampaignCategoryDetailComponent from '@/entities/campaign-category/campaign-category-details.vue';
import CampaignCategoryClass from '@/entities/campaign-category/campaign-category-details.component';
import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('CampaignCategory Management Detail Component', () => {
    let wrapper: Wrapper<CampaignCategoryClass>;
    let comp: CampaignCategoryClass;
    let campaignCategoryServiceStub: SinonStubbedInstance<CampaignCategoryService>;

    beforeEach(() => {
      campaignCategoryServiceStub = sinon.createStubInstance<CampaignCategoryService>(CampaignCategoryService);

      wrapper = shallowMount<CampaignCategoryClass>(CampaignCategoryDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { campaignCategoryService: () => campaignCategoryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCampaignCategory = { id: 123 };
        campaignCategoryServiceStub.find.resolves(foundCampaignCategory);

        // WHEN
        comp.retrieveCampaignCategory(123);
        await comp.$nextTick();

        // THEN
        expect(comp.campaignCategory).toBe(foundCampaignCategory);
      });
    });
  });
});
