/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InfluencerInfoUpdateComponent from '@/entities/influencer-info/influencer-info-update.vue';
import InfluencerInfoClass from '@/entities/influencer-info/influencer-info-update.component';
import InfluencerInfoService from '@/entities/influencer-info/influencer-info.service';

import UserService from '@/admin/user-management/user-management.service';

import SocialNetworkLinkService from '@/entities/social-network-link/social-network-link.service';

import CampaignCategoryService from '@/entities/campaign-category/campaign-category.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InfluencerInfo Management Update Component', () => {
    let wrapper: Wrapper<InfluencerInfoClass>;
    let comp: InfluencerInfoClass;
    let influencerInfoServiceStub: SinonStubbedInstance<InfluencerInfoService>;

    beforeEach(() => {
      influencerInfoServiceStub = sinon.createStubInstance<InfluencerInfoService>(InfluencerInfoService);

      wrapper = shallowMount<InfluencerInfoClass>(InfluencerInfoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          influencerInfoService: () => influencerInfoServiceStub,

          userService: () => new UserService(),

          socialNetworkLinkService: () => new SocialNetworkLinkService(),

          campaignCategoryService: () => new CampaignCategoryService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.influencerInfo = entity;
        influencerInfoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(influencerInfoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.influencerInfo = entity;
        influencerInfoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(influencerInfoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
