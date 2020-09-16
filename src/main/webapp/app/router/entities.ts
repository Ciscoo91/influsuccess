import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const UserExtra = () => import('@/entities/user-extra/user-extra.vue');
// prettier-ignore
const UserExtraUpdate = () => import('@/entities/user-extra/user-extra-update.vue');
// prettier-ignore
const UserExtraDetails = () => import('@/entities/user-extra/user-extra-details.vue');
// prettier-ignore
const Campaign = () => import('@/entities/campaign/campaign.vue');
// prettier-ignore
const CampaignUpdate = () => import('@/entities/campaign/campaign-update.vue');
// prettier-ignore
const CampaignDetails = () => import('@/entities/campaign/campaign-details.vue');
// prettier-ignore
const InstagInfluencer = () => import('@/entities/instag-influencer/instag-influencer.vue');
// prettier-ignore
const InstagInfluencerUpdate = () => import('@/entities/instag-influencer/instag-influencer-update.vue');
// prettier-ignore
const InstagInfluencerDetails = () => import('@/entities/instag-influencer/instag-influencer-details.vue');
// prettier-ignore
const Contact = () => import('@/entities/contact/contact.vue');
// prettier-ignore
const ContactUpdate = () => import('@/entities/contact/contact-update.vue');
// prettier-ignore
const ContactDetails = () => import('@/entities/contact/contact-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/user-extra',
    name: 'UserExtra',
    component: UserExtra,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/user-extra/new',
    name: 'UserExtraCreate',
    component: UserExtraUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/user-extra/:userExtraId/edit',
    name: 'UserExtraEdit',
    component: UserExtraUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/user-extra/:userExtraId/view',
    name: 'UserExtraView',
    component: UserExtraDetails,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/campaign',
    name: 'Campaign',
    component: Campaign,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/campaign/new',
    name: 'CampaignCreate',
    component: CampaignUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/campaign/:campaignId/edit',
    name: 'CampaignEdit',
    component: CampaignUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/campaign/:campaignId/view',
    name: 'CampaignView',
    component: CampaignDetails,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/instag-influencer',
    name: 'InstagInfluencer',
    component: InstagInfluencer,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/instag-influencer/new',
    name: 'InstagInfluencerCreate',
    component: InstagInfluencerUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/instag-influencer/:instagInfluencerId/edit',
    name: 'InstagInfluencerEdit',
    component: InstagInfluencerUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/instag-influencer/:instagInfluencerId/view',
    name: 'InstagInfluencerView',
    component: InstagInfluencerDetails,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/contact/new',
    name: 'ContactCreate',
    component: ContactUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/contact/:contactId/edit',
    name: 'ContactEdit',
    component: ContactUpdate,
    meta: { authorities: [Authority.OTHER] },
  },
  {
    path: '/contact/:contactId/view',
    name: 'ContactView',
    component: ContactDetails,
    meta: { authorities: [Authority.OTHER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
