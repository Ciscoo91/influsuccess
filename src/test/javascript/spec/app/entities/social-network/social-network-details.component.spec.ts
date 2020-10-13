/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SocialNetworkDetailComponent from '@/entities/social-network/social-network-details.vue';
import SocialNetworkClass from '@/entities/social-network/social-network-details.component';
import SocialNetworkService from '@/entities/social-network/social-network.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SocialNetwork Management Detail Component', () => {
    let wrapper: Wrapper<SocialNetworkClass>;
    let comp: SocialNetworkClass;
    let socialNetworkServiceStub: SinonStubbedInstance<SocialNetworkService>;

    beforeEach(() => {
      socialNetworkServiceStub = sinon.createStubInstance<SocialNetworkService>(SocialNetworkService);

      wrapper = shallowMount<SocialNetworkClass>(SocialNetworkDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { socialNetworkService: () => socialNetworkServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSocialNetwork = { id: 123 };
        socialNetworkServiceStub.find.resolves(foundSocialNetwork);

        // WHEN
        comp.retrieveSocialNetwork(123);
        await comp.$nextTick();

        // THEN
        expect(comp.socialNetwork).toBe(foundSocialNetwork);
      });
    });
  });
});
