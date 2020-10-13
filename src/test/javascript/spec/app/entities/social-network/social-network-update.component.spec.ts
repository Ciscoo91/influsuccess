/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SocialNetworkUpdateComponent from '@/entities/social-network/social-network-update.vue';
import SocialNetworkClass from '@/entities/social-network/social-network-update.component';
import SocialNetworkService from '@/entities/social-network/social-network.service';

import CampaignService from '@/entities/campaign/campaign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SocialNetwork Management Update Component', () => {
    let wrapper: Wrapper<SocialNetworkClass>;
    let comp: SocialNetworkClass;
    let socialNetworkServiceStub: SinonStubbedInstance<SocialNetworkService>;

    beforeEach(() => {
      socialNetworkServiceStub = sinon.createStubInstance<SocialNetworkService>(SocialNetworkService);

      wrapper = shallowMount<SocialNetworkClass>(SocialNetworkUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          socialNetworkService: () => socialNetworkServiceStub,

          campaignService: () => new CampaignService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.socialNetwork = entity;
        socialNetworkServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(socialNetworkServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.socialNetwork = entity;
        socialNetworkServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(socialNetworkServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
