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
const InfluencerInfo = () => import('@/entities/influencer-info/influencer-info.vue');
// prettier-ignore
const InfluencerInfoUpdate = () => import('@/entities/influencer-info/influencer-info-update.vue');
// prettier-ignore
const InfluencerInfoDetails = () => import('@/entities/influencer-info/influencer-info-details.vue');
// prettier-ignore
const SocialNetworkLink = () => import('@/entities/social-network-link/social-network-link.vue');
// prettier-ignore
const SocialNetworkLinkUpdate = () => import('@/entities/social-network-link/social-network-link-update.vue');
// prettier-ignore
const SocialNetworkLinkDetails = () => import('@/entities/social-network-link/social-network-link-details.vue');
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
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
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
    path: '/influencer-info',
    name: 'InfluencerInfo',
    component: InfluencerInfo,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/new',
    name: 'InfluencerInfoCreate',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/edit',
    name: 'InfluencerInfoEdit',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/view',
    name: 'InfluencerInfoView',
    component: InfluencerInfoDetails,
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
    path: '/influencer-info',
    name: 'InfluencerInfo',
    component: InfluencerInfo,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/new',
    name: 'InfluencerInfoCreate',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/edit',
    name: 'InfluencerInfoEdit',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/view',
    name: 'InfluencerInfoView',
    component: InfluencerInfoDetails,
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
    path: '/influencer-info',
    name: 'InfluencerInfo',
    component: InfluencerInfo,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/new',
    name: 'InfluencerInfoCreate',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/edit',
    name: 'InfluencerInfoEdit',
    component: InfluencerInfoUpdate,
    meta: { authorities: [Authority.ADVERTISER] },
  },
  {
    path: '/influencer-info/:influencerInfoId/view',
    name: 'InfluencerInfoView',
    component: InfluencerInfoDetails,
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
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
