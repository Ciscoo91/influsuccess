import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';
import { CampaignCategoryEnum, ICampaignCategory } from '@/shared/model/campaign-category.model';
import { SocialNetworkEnum } from './social-network.model';

export interface IInfluencer {
  id?: number;
  username?: string;
  email?: string;
  totalFollowers?: number;
  socialNetworkLinks?: ISocialNetworkLink[];
  categories?: ICampaignCategory[];
}

export class Influencer implements IInfluencer {
  constructor(
    public id?: number,
    public username?: string,
    public email?: string,
    public totalFollowers?: number,
    public socialNetworkLinks?: ISocialNetworkLink[],
    public categories?: ICampaignCategory[]
  ) {}
}

export class InfluencerSearch {
  constructor(
    public username?: string,
    public socialNetworkName?: SocialNetworkEnum,
    public campaignCategoryEnum?: CampaignCategoryEnum,
    public countryCode?: string,
    public totalFollowerMin?: number
  ) {}
}
