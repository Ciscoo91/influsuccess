/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SocialNetworkComponent from '@/entities/social-network/social-network.vue';
import SocialNetworkClass from '@/entities/social-network/social-network.component';
import SocialNetworkService from '@/entities/social-network/social-network.service';

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
  describe('SocialNetwork Management Component', () => {
    let wrapper: Wrapper<SocialNetworkClass>;
    let comp: SocialNetworkClass;
    let socialNetworkServiceStub: SinonStubbedInstance<SocialNetworkService>;

    beforeEach(() => {
      socialNetworkServiceStub = sinon.createStubInstance<SocialNetworkService>(SocialNetworkService);
      socialNetworkServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SocialNetworkClass>(SocialNetworkComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          socialNetworkService: () => socialNetworkServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      socialNetworkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSocialNetworks();
      await comp.$nextTick();

      // THEN
      expect(socialNetworkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.socialNetworks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      socialNetworkServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeSocialNetwork();
      await comp.$nextTick();

      // THEN
      expect(socialNetworkServiceStub.delete.called).toBeTruthy();
      expect(socialNetworkServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
