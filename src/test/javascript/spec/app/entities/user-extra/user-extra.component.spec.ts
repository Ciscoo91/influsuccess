/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UserExtraComponent from '@/entities/user-extra/user-extra.vue';
import UserExtraClass from '@/entities/user-extra/user-extra.component';
import UserExtraService from '@/entities/user-extra/user-extra.service';

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
  describe('UserExtra Management Component', () => {
    let wrapper: Wrapper<UserExtraClass>;
    let comp: UserExtraClass;
    let userExtraServiceStub: SinonStubbedInstance<UserExtraService>;

    beforeEach(() => {
      userExtraServiceStub = sinon.createStubInstance<UserExtraService>(UserExtraService);
      userExtraServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UserExtraClass>(UserExtraComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          userExtraService: () => userExtraServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      userExtraServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUserExtras();
      await comp.$nextTick();

      // THEN
      expect(userExtraServiceStub.retrieve.called).toBeTruthy();
      expect(comp.userExtras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      userExtraServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeUserExtra();
      await comp.$nextTick();

      // THEN
      expect(userExtraServiceStub.delete.called).toBeTruthy();
      expect(userExtraServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
