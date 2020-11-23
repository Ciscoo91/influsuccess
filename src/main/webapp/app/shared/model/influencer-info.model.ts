import { IUser } from '@/shared/model/user.model';
import { ISocialNetworkLink } from '@/shared/model/social-network-link.model';
import { ICampaignCategory } from '@/shared/model/campaign-category.model';

export interface IInfluencer {
  id?: number;
  user?: IUser;
  socialNetworkLinks?: ISocialNetworkLink[];
  categories?: ICampaignCategory[];
}

export class Influencer implements IInfluencer {
  constructor(
    public id?: number,
    public user?: IUser,
    public socialNetworkLinks?: ISocialNetworkLink[],
    public categories?: ICampaignCategory[]
  ) {}
}
