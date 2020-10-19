/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SocialNetworkLinkComponent from '@/entities/social-network-link/social-network-link.vue';
import SocialNetworkLinkClass from '@/entities/social-network-link/social-network-link.component';
import SocialNetworkLinkService from '@/entities/social-network-link/social-network-link.service';

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
  describe('SocialNetworkLink Management Component', () => {
    let wrapper: Wrapper<SocialNetworkLinkClass>;
    let comp: SocialNetworkLinkClass;
    let socialNetworkLinkServiceStub: SinonStubbedInstance<SocialNetworkLinkService>;

    beforeEach(() => {
      socialNetworkLinkServiceStub = sinon.createStubInstance<SocialNetworkLinkService>(SocialNetworkLinkService);
      socialNetworkLinkServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SocialNetworkLinkClass>(SocialNetworkLinkComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          socialNetworkLinkService: () => socialNetworkLinkServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      socialNetworkLinkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSocialNetworkLinks();
      await comp.$nextTick();

      // THEN
      expect(socialNetworkLinkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.socialNetworkLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      socialNetworkLinkServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeSocialNetworkLink();
      await comp.$nextTick();

      // THEN
      expect(socialNetworkLinkServiceStub.delete.called).toBeTruthy();
      expect(socialNetworkLinkServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
