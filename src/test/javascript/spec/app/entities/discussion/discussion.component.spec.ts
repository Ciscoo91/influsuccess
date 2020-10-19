/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import DiscussionComponent from '@/entities/discussion/discussion.vue';
import DiscussionClass from '@/entities/discussion/discussion.component';
import DiscussionService from '@/entities/discussion/discussion.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Discussion Management Component', () => {
    let wrapper: Wrapper<DiscussionClass>;
    let comp: DiscussionClass;
    let discussionServiceStub: SinonStubbedInstance<DiscussionService>;

    beforeEach(() => {
      discussionServiceStub = sinon.createStubInstance<DiscussionService>(DiscussionService);
      discussionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<DiscussionClass>(DiscussionComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          discussionService: () => discussionServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      discussionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllDiscussions();
      await comp.$nextTick();

      // THEN
      expect(discussionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.discussions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      discussionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeDiscussion();
      await comp.$nextTick();

      // THEN
      expect(discussionServiceStub.delete.called).toBeTruthy();
      expect(discussionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
