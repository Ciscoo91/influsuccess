/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UserExtraDetailComponent from '@/entities/user-extra/user-extra-details.vue';
import UserExtraClass from '@/entities/user-extra/user-extra-details.component';
import UserExtraService from '@/entities/user-extra/user-extra.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UserExtra Management Detail Component', () => {
    let wrapper: Wrapper<UserExtraClass>;
    let comp: UserExtraClass;
    let userExtraServiceStub: SinonStubbedInstance<UserExtraService>;

    beforeEach(() => {
      userExtraServiceStub = sinon.createStubInstance<UserExtraService>(UserExtraService);

      wrapper = shallowMount<UserExtraClass>(UserExtraDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { userExtraService: () => userExtraServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUserExtra = { id: 123 };
        userExtraServiceStub.find.resolves(foundUserExtra);

        // WHEN
        comp.retrieveUserExtra(123);
        await comp.$nextTick();

        // THEN
        expect(comp.userExtra).toBe(foundUserExtra);
      });
    });
  });
});
