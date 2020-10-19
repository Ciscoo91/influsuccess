import { ISocialNetwork } from '@/shared/model/social-network.model';
import { IInfluencerInfo } from '@/shared/model/influencer-info.model';

export interface ISocialNetworkLink {
  id?: number;
  link?: string;
  socialNetwork?: ISocialNetwork;
  influencerInfo?: IInfluencerInfo;
}

export class SocialNetworkLink implements ISocialNetworkLink {
  constructor(public id?: number, public link?: string, public socialNetwork?: ISocialNetwork, public influencerInfo?: IInfluencerInfo) {}
}
