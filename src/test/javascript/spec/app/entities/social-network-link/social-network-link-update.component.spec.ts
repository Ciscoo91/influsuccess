/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SocialNetworkLinkUpdateComponent from '@/entities/social-network-link/social-network-link-update.vue';
import SocialNetworkLinkClass from '@/entities/social-network-link/social-network-link-update.component';
import SocialNetworkLinkService from '@/entities/social-network-link/social-network-link.service';

import SocialNetworkService from '@/entities/social-network/social-network.service';

import InfluencerInfoService from '@/entities/influencer-info/influencer-info.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SocialNetworkLink Management Update Component', () => {
    let wrapper: Wrapper<SocialNetworkLinkClass>;
    let comp: SocialNetworkLinkClass;
    let socialNetworkLinkServiceStub: SinonStubbedInstance<SocialNetworkLinkService>;

    beforeEach(() => {
      socialNetworkLinkServiceStub = sinon.createStubInstance<SocialNetworkLinkService>(SocialNetworkLinkService);

      wrapper = shallowMount<SocialNetworkLinkClass>(SocialNetworkLinkUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          socialNetworkLinkService: () => socialNetworkLinkServiceStub,

          socialNetworkService: () => new SocialNetworkService(),

          influencerInfoService: () => new InfluencerInfoService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.socialNetworkLink = entity;
        socialNetworkLinkServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(socialNetworkLinkServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.socialNetworkLink = entity;
        socialNetworkLinkServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(socialNetworkLinkServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
