import { Authority } from '@/shared/security/authority';

const Register = () => import('@/account/register/register.vue');
const Activate = () => import('@/account/activate/activate.vue');
const ResetPasswordInit = () => import('@/account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('@/account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('@/account/change-password/change-password.vue');
const Settings = () => import('@/account/settings/settings.vue');
const Billing = () => import('@/account/billing/billing.vue');
// const first_step_form = () => import('@/account/billing/forms/first_step_form.component.vue');
// const second_step_form = () => import('@/account/billing/forms/second_step_form.component.vue');
const FirstStepForm = () => '@/account/billing/forms/first_step_form.vue';
const SecondStepForm = () => '@/account/billing/forms/second_step_form.vue';

export default [
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/account/activate',
    name: 'Activate',
    component: Activate,
  },
  {
    path: '/account/reset/request',
    name: 'ResetPasswordInit',
    component: ResetPasswordInit,
  },
  {
    path: '/account/reset/finish',
    name: 'ResetPasswordFinish',
    component: ResetPasswordFinish,
  },
  {
    path: '/account/password',
    name: 'ChangePassword',
    component: ChangePassword,
    meta: { authorities: [Authority.ADVERTISER, Authority.INFLUENCER] },
  },
  {
    path: '/account/settings',
    name: 'Settings',
    component: Settings,
    meta: { authorities: [Authority.ADVERTISER, Authority.INFLUENCER] },
  },
  {
    path: '/account/billing',
    name: 'Billing',
    component: Billing,
    // meta: { authorities: [Authority.ADVERTISER, Authority.INFLUENCER] },
  },
  {
    path: '/account/billing/success',
    name: 'successBilling',
    component: FirstStepForm,
    meta: { authorities: [Authority.ADVERTISER, Authority.INFLUENCER] },
  },
  {
    path: '/account/billing/cancel',
    name: 'cancelBilling',
    component: SecondStepForm,
    meta: { authorities: [Authority.ADVERTISER, Authority.INFLUENCER] },
  },
];
