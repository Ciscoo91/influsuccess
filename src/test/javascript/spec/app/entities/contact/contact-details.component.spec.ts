/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContactDetailComponent from '@/entities/contact/contact-details.vue';
import ContactClass from '@/entities/contact/contact-details.component';
import ContactService from '@/entities/contact/contact.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Contact Management Detail Component', () => {
    let wrapper: Wrapper<ContactClass>;
    let comp: ContactClass;
    let contactServiceStub: SinonStubbedInstance<ContactService>;

    beforeEach(() => {
      contactServiceStub = sinon.createStubInstance<ContactService>(ContactService);

      wrapper = shallowMount<ContactClass>(ContactDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { contactService: () => contactServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundContact = { id: 123 };
        contactServiceStub.find.resolves(foundContact);

        // WHEN
        comp.retrieveContact(123);
        await comp.$nextTick();

        // THEN
        expect(comp.contact).toBe(foundContact);
      });
    });
  });
});
