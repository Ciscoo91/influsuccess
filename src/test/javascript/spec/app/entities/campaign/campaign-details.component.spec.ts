/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CampaignDetailComponent from '@/entities/campaign/campaign-details.vue';
import CampaignClass from '@/entities/campaign/campaign-details.component';
import CampaignService from '@/entities/campaign/campaign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Campaign Management Detail Component', () => {
    let wrapper: Wrapper<CampaignClass>;
    let comp: CampaignClass;
    let campaignServiceStub: SinonStubbedInstance<CampaignService>;

    beforeEach(() => {
      campaignServiceStub = sinon.createStubInstance<CampaignService>(CampaignService);

      wrapper = shallowMount<CampaignClass>(CampaignDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { campaignService: () => campaignServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCampaign = { id: 123 };
        campaignServiceStub.find.resolves(foundCampaign);

        // WHEN
        comp.retrieveCampaign(123);
        await comp.$nextTick();

        // THEN
        expect(comp.campaign).toBe(foundCampaign);
      });
    });
  });
});
