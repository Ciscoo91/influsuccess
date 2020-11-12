/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InstagInfluencerDetailComponent from '@/entities/instag-influencer/instag-influencer-details.vue';
import InstagInfluencerClass from '@/entities/instag-influencer/instag-influencer-details.component';
import InstagInfluencerService from '@/entities/instag-influencer/instag-influencer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InstagInfluencer Management Detail Component', () => {
    let wrapper: Wrapper<InstagInfluencerClass>;
    let comp: InstagInfluencerClass;
    let instagInfluencerServiceStub: SinonStubbedInstance<InstagInfluencerService>;

    beforeEach(() => {
      instagInfluencerServiceStub = sinon.createStubInstance<InstagInfluencerService>(InstagInfluencerService);

      wrapper = shallowMount<InstagInfluencerClass>(InstagInfluencerDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { instagInfluencerService: () => instagInfluencerServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInstagInfluencer = { id: 123 };
        instagInfluencerServiceStub.find.resolves(foundInstagInfluencer);

        // WHEN
        comp.retrieveInstagInfluencer(123);
        await comp.$nextTick();

        // THEN
        expect(comp.instagInfluencer).toBe(foundInstagInfluencer);
      });
    });
  });
});
