import { ISocialNetwork } from '@/shared/model/social-network.model';
import { IInfluencer } from '@/shared/model/influencer-info.model';

export interface ISocialNetworkLink {
  id?: number;
  link?: string;
  socialNetwork?: ISocialNetwork;
  influencer?: IInfluencer;
}

export class SocialNetworkLink implements ISocialNetworkLink {
  constructor(public id?: number, public link?: string, public socialNetwork?: ISocialNetwork, public influencer?: IInfluencer) {}
}
