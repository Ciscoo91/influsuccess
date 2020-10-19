/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UserExtraUpdateComponent from '@/entities/user-extra/user-extra-update.vue';
import UserExtraClass from '@/entities/user-extra/user-extra-update.component';
import UserExtraService from '@/entities/user-extra/user-extra.service';

import UserService from '@/admin/user-management/user-management.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UserExtra Management Update Component', () => {
    let wrapper: Wrapper<UserExtraClass>;
    let comp: UserExtraClass;
    let userExtraServiceStub: SinonStubbedInstance<UserExtraService>;

    beforeEach(() => {
      userExtraServiceStub = sinon.createStubInstance<UserExtraService>(UserExtraService);

      wrapper = shallowMount<UserExtraClass>(UserExtraUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          userExtraService: () => userExtraServiceStub,

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.userExtra = entity;
        userExtraServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userExtraServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.userExtra = entity;
        userExtraServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userExtraServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
