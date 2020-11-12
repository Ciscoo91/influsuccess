/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MessageUpdateComponent from '@/entities/message/message-update.vue';
import MessageClass from '@/entities/message/message-update.component';
import MessageService from '@/entities/message/message.service';

import UserService from '@/admin/user-management/user-management.service';

import DiscussionService from '@/entities/discussion/discussion.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Message Management Update Component', () => {
    let wrapper: Wrapper<MessageClass>;
    let comp: MessageClass;
    let messageServiceStub: SinonStubbedInstance<MessageService>;

    beforeEach(() => {
      messageServiceStub = sinon.createStubInstance<MessageService>(MessageService);

      wrapper = shallowMount<MessageClass>(MessageUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          messageService: () => messageServiceStub,

          userService: () => new UserService(),

          discussionService: () => new DiscussionService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.message = entity;
        messageServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messageServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.message = entity;
        messageServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(messageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
