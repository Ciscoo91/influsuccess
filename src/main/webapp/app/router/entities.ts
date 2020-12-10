import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const CampaignCategory = () => import('@/entities/campaign-category/campaign-category.vue');
// prettier-ignore
const CampaignCategoryUpdate = () => import('@/entities/campaign-category/campaign-category-update.vue');
// prettier-ignore
const CampaignCategoryDetails = () => import('@/entities/campaign-category/campaign-category-details.vue');
// prettier-ignore
const SocialNetwork = () => import('@/entities/social-network/social-network.vue');
// prettier-ignore
const SocialNetworkUpdate = () => import('@/entities/social-network/social-network-update.vue');
// prettier-ignore
const SocialNetworkDetails = () => import('@/entities/social-network/social-network-details.vue');
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
const Influencer = () => import('@/entities/influencer/influencer.vue');
// prettier-ignore
const InfluencerUpdate = () => import('@/entities/influencer/influencer-update.vue');
// prettier-ignore
const InfluencerDetails = () => import('@/entities/influencer/influencer-details.vue');
// prettier-ignore
const SocialNetworkLink = () => import('@/entities/social-network-link/social-network-link.vue');
// prettier-ignore
const SocialNetworkLinkUpdate = () => import('@/entities/social-network-link/social-network-link-update.vue');
// prettier-ignore
const SocialNetworkLinkDetails = () => import('@/entities/social-network-link/social-network-link-details.vue');
// prettier-ignore
const Message = () => import('@/entities/message/message.vue');
// prettier-ignore
const MessageUpdate = () => import('@/entities/message/message-update.vue');
// prettier-ignore
const MessageDetails = () => import('@/entities/message/message-details.vue');
// prettier-ignore
const Discussion = () => import('@/entities/discussion/discussion.vue');
// prettier-ignore
const DiscussionUpdate = () => import('@/entities/discussion/discussion-update.vue');
// prettier-ignore
const DiscussionDetails = () => import('@/entities/discussion/discussion-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/campaign-category',
    name: 'CampaignCategory',
    component: CampaignCategory,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign-category/new',
    name: 'CampaignCategoryCreate',
    component: CampaignCategoryUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign-category/:campaignCategoryId/edit',
    name: 'CampaignCategoryEdit',
    component: CampaignCategoryUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign-category/:campaignCategoryId/view',
    name: 'CampaignCategoryView',
    component: CampaignCategoryDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network',
    name: 'SocialNetwork',
    component: SocialNetwork,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network/new',
    name: 'SocialNetworkCreate',
    component: SocialNetworkUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network/:socialNetworkId/edit',
    name: 'SocialNetworkEdit',
    component: SocialNetworkUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network/:socialNetworkId/view',
    name: 'SocialNetworkView',
    component: SocialNetworkDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/user-extra',
    name: 'UserExtra',
    component: UserExtra,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/user-extra/new',
    name: 'UserExtraCreate',
    component: UserExtraUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/user-extra/:userExtraId/edit',
    name: 'UserExtraEdit',
    component: UserExtraUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/user-extra/:userExtraId/view',
    name: 'UserExtraView',
    component: UserExtraDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign',
    name: 'Campaign',
    component: Campaign,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign/new',
    name: 'CampaignCreate',
    component: CampaignUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign/:campaignId/edit',
    name: 'CampaignEdit',
    component: CampaignUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/campaign/:campaignId/view',
    name: 'CampaignView',
    component: CampaignDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer',
    name: 'Influencer',
    component: Influencer,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer/new',
    name: 'InfluencerCreate',
    component: InfluencerUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer/:influencerId/edit',
    name: 'InfluencerEdit',
    component: InfluencerUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer/:influencerId/view',
    name: 'InfluencerView',
    component: InfluencerDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network-link',
    name: 'SocialNetworkLink',
    component: SocialNetworkLink,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network-link/new',
    name: 'SocialNetworkLinkCreate',
    component: SocialNetworkLinkUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network-link/:socialNetworkLinkId/edit',
    name: 'SocialNetworkLinkEdit',
    component: SocialNetworkLinkUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/social-network-link/:socialNetworkLinkId/view',
    name: 'SocialNetworkLinkView',
    component: SocialNetworkLinkDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/message',
    name: 'Message',
    component: Message,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/message/new',
    name: 'MessageCreate',
    component: MessageUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/message/:messageId/edit',
    name: 'MessageEdit',
    component: MessageUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/message/:messageId/view',
    name: 'MessageView',
    component: MessageDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/discussion',
    name: 'Discussion',
    component: Discussion,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/discussion/new',
    name: 'DiscussionCreate',
    component: DiscussionUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/discussion/:discussionId/edit',
    name: 'DiscussionEdit',
    component: DiscussionUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/discussion/:discussionId/view',
    name: 'DiscussionView',
    component: DiscussionDetails,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
