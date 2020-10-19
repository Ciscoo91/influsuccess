import { ICampaign } from '@/shared/model/campaign.model';

export interface ISocialNetwork {
  id?: number;
  name?: string;
  campaign?: ICampaign;
}

export class SocialNetwork implements ISocialNetwork {
  constructor(public id?: number, public name?: string, public campaign?: ICampaign) {}
}
