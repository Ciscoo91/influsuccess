/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SocialNetworkLinkDetailComponent from '@/entities/social-network-link/social-network-link-details.vue';
import SocialNetworkLinkClass from '@/entities/social-network-link/social-network-link-details.component';
import SocialNetworkLinkService from '@/entities/social-network-link/social-network-link.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SocialNetworkLink Management Detail Component', () => {
    let wrapper: Wrapper<SocialNetworkLinkClass>;
    let comp: SocialNetworkLinkClass;
    let socialNetworkLinkServiceStub: SinonStubbedInstance<SocialNetworkLinkService>;

    beforeEach(() => {
      socialNetworkLinkServiceStub = sinon.createStubInstance<SocialNetworkLinkService>(SocialNetworkLinkService);

      wrapper = shallowMount<SocialNetworkLinkClass>(SocialNetworkLinkDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { socialNetworkLinkService: () => socialNetworkLinkServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSocialNetworkLink = { id: 123 };
        socialNetworkLinkServiceStub.find.resolves(foundSocialNetworkLink);

        // WHEN
        comp.retrieveSocialNetworkLink(123);
        await comp.$nextTick();

        // THEN
        expect(comp.socialNetworkLink).toBe(foundSocialNetworkLink);
      });
    });
  });
});
