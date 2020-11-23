import { IInfluencer } from '@/shared/model/influencer-info.model';
import { ICampaign } from '@/shared/model/campaign.model';

export interface ICampaignCategory {
  id?: number;
  name?: string;
  influencer?: IInfluencer;
  campaign?: ICampaign;
}

export class CampaignCategory implements ICampaignCategory {
  constructor(public id?: number, public name?: string, public influencer?: IInfluencer, public campaign?: ICampaign) {}
}
