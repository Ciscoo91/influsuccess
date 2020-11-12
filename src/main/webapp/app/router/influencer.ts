import { Authority } from '@/shared/security/authority';

const InfluencerDashboardComponent = () => import('@/dashboard/influencer/influencerDashboard.vue');
export default [
  {
    path: '/influencers/dashboard',
    name: 'InfluencerDashboardView',
    component: InfluencerDashboardComponent,
    meta: { authorities: [Authority.INFLUENCER] },
  },
];
