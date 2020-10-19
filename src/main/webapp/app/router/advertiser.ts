import { Authority } from '@/shared/security/authority';

const AdvDashboardComponent = ()=> import('@/advertiser/dashboard/advDashboard.vue');
export default [
  {
    path: '/advertisers/dashboard',
    name: 'AdvDashboardView',
    component: AdvDashboardComponent,
    meta: { authorities: [Authority.ADVERTISER] },
  },
];
