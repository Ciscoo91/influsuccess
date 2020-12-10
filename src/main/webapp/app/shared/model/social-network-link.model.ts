import { SocialNetworkEnum } from '@/shared/model/social-network.model';

export interface ISocialNetworkLink {
  id?: number;
  link?: string;
  socialNetworkName?: SocialNetworkEnum;
}

export class SocialNetworkLink implements ISocialNetworkLink {
  constructor(public id?: number, public link?: string, public socialNetworkName?: SocialNetworkEnum) {}
}
