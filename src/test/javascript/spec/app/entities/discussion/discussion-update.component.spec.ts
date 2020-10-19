/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import DiscussionUpdateComponent from '@/entities/discussion/discussion-update.vue';
import DiscussionClass from '@/entities/discussion/discussion-update.component';
import DiscussionService from '@/entities/discussion/discussion.service';

import UserService from '@/admin/user-management/user-management.service';

import MessageService from '@/entities/message/message.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Discussion Management Update Component', () => {
    let wrapper: Wrapper<DiscussionClass>;
    let comp: DiscussionClass;
    let discussionServiceStub: SinonStubbedInstance<DiscussionService>;

    beforeEach(() => {
      discussionServiceStub = sinon.createStubInstance<DiscussionService>(DiscussionService);

      wrapper = shallowMount<DiscussionClass>(DiscussionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          discussionService: () => discussionServiceStub,

          userService: () => new UserService(),

          messageService: () => new MessageService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.discussion = entity;
        discussionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(discussionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.discussion = entity;
        discussionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(discussionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
