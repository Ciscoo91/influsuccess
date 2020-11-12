/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DiscussionDetailComponent from '@/entities/discussion/discussion-details.vue';
import DiscussionClass from '@/entities/discussion/discussion-details.component';
import DiscussionService from '@/entities/discussion/discussion.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Discussion Management Detail Component', () => {
    let wrapper: Wrapper<DiscussionClass>;
    let comp: DiscussionClass;
    let discussionServiceStub: SinonStubbedInstance<DiscussionService>;

    beforeEach(() => {
      discussionServiceStub = sinon.createStubInstance<DiscussionService>(DiscussionService);

      wrapper = shallowMount<DiscussionClass>(DiscussionDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { discussionService: () => discussionServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDiscussion = { id: 123 };
        discussionServiceStub.find.resolves(foundDiscussion);

        // WHEN
        comp.retrieveDiscussion(123);
        await comp.$nextTick();

        // THEN
        expect(comp.discussion).toBe(foundDiscussion);
      });
    });
  });
});
