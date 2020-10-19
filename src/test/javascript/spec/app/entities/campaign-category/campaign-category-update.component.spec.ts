/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CampaignCategoryUpdateComponent from '@/entities/campaign-category/campaign-category-update.vue';
import CampaignCategoryClass from '@/entities/campaign-category/campaign-category-update.component';
import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';

import InfluencerInfoService from '@/entities/influencer-info/influencer-info.service';

import CampaignService from '@/entities/campaign/campaign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('CampaignCategory Management Update Component', () => {
    let wrapper: Wrapper<CampaignCategoryClass>;
    let comp: CampaignCategoryClass;
    let campaignCategoryServiceStub: SinonStubbedInstance<CampaignCategoryService>;

    beforeEach(() => {
      campaignCategoryServiceStub = sinon.createStubInstance<CampaignCategoryService>(CampaignCategoryService);

      wrapper = shallowMount<CampaignCategoryClass>(CampaignCategoryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          campaignCategoryService: () => campaignCategoryServiceStub,

          influencerInfoService: () => new InfluencerInfoService(),

          campaignService: () => new CampaignService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.campaignCategory = entity;
        campaignCategoryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(campaignCategoryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.campaignCategory = entity;
        campaignCategoryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(campaignCategoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
