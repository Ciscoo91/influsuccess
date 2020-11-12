/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InfluencerInfoDetailComponent from '@/entities/influencer-info/influencer-info-details.vue';
import InfluencerInfoClass from '@/entities/influencer-info/influencer-info-details.component';
import InfluencerInfoService from '@/entities/influencer-info/influencer-info.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('InfluencerInfo Management Detail Component', () => {
    let wrapper: Wrapper<InfluencerInfoClass>;
    let comp: InfluencerInfoClass;
    let influencerInfoServiceStub: SinonStubbedInstance<InfluencerInfoService>;

    beforeEach(() => {
      influencerInfoServiceStub = sinon.createStubInstance<InfluencerInfoService>(InfluencerInfoService);

      wrapper = shallowMount<InfluencerInfoClass>(InfluencerInfoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { influencerInfoService: () => influencerInfoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInfluencerInfo = { id: 123 };
        influencerInfoServiceStub.find.resolves(foundInfluencerInfo);

        // WHEN
        comp.retrieveInfluencerInfo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.influencerInfo).toBe(foundInfluencerInfo);
      });
    });
  });
});
