/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CampaignUpdateComponent from '@/entities/campaign/campaign-update.vue';
import CampaignClass from '@/entities/campaign/campaign-update.component';
import CampaignService from '@/entities/campaign/campaign.service';

import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';

import SocialNetworkService from '@/entities/social-network/social-network.service';

import UserService from '@/admin/user-management/user-management.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Campaign Management Update Component', () => {
    let wrapper: Wrapper<CampaignClass>;
    let comp: CampaignClass;
    let campaignServiceStub: SinonStubbedInstance<CampaignService>;

    beforeEach(() => {
      campaignServiceStub = sinon.createStubInstance<CampaignService>(CampaignService);

      wrapper = shallowMount<CampaignClass>(CampaignUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          campaignService: () => campaignServiceStub,

          campaignCategoryService: () => new CampaignCategoryService(),

          socialNetworkService: () => new SocialNetworkService(),

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.campaign = entity;
        campaignServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(campaignServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.campaign = entity;
        campaignServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(campaignServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
