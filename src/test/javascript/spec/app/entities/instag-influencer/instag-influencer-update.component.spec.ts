/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InstagInfluencerUpdateComponent from '@/entities/instag-influencer/instag-influencer-update.vue';
import InstagInfluencerClass from '@/entities/instag-influencer/instag-influencer-update.component';
import InstagInfluencerService from '@/entities/instag-influencer/instag-influencer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('InstagInfluencer Management Update Component', () => {
    let wrapper: Wrapper<InstagInfluencerClass>;
    let comp: InstagInfluencerClass;
    let instagInfluencerServiceStub: SinonStubbedInstance<InstagInfluencerService>;

    beforeEach(() => {
      instagInfluencerServiceStub = sinon.createStubInstance<InstagInfluencerService>(InstagInfluencerService);

      wrapper = shallowMount<InstagInfluencerClass>(InstagInfluencerUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          instagInfluencerService: () => instagInfluencerServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.instagInfluencer = entity;
        instagInfluencerServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(instagInfluencerServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.instagInfluencer = entity;
        instagInfluencerServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(instagInfluencerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
