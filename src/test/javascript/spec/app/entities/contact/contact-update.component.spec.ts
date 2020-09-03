/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ContactUpdateComponent from '@/entities/contact/contact-update.vue';
import ContactClass from '@/entities/contact/contact-update.component';
import ContactService from '@/entities/contact/contact.service';

import UserService from '@/admin/user-management/user-management.service';

import CampaignService from '@/entities/campaign/campaign.service';

import InstagInfluencerService from '@/entities/instag-influencer/instag-influencer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Contact Management Update Component', () => {
    let wrapper: Wrapper<ContactClass>;
    let comp: ContactClass;
    let contactServiceStub: SinonStubbedInstance<ContactService>;

    beforeEach(() => {
      contactServiceStub = sinon.createStubInstance<ContactService>(ContactService);

      wrapper = shallowMount<ContactClass>(ContactUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          contactService: () => contactServiceStub,

          userService: () => new UserService(),

          campaignService: () => new CampaignService(),

          instagInfluencerService: () => new InstagInfluencerService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.contact = entity;
        contactServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contactServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.contact = entity;
        contactServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contactServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
