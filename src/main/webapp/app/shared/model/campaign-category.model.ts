import { IInfluencerInfo } from '@/shared/model/influencer-info.model';
import { ICampaign } from '@/shared/model/campaign.model';

export interface ICampaignCategory {
  id?: number;
  name?: string;
  influencerInfo?: IInfluencerInfo;
  campaign?: ICampaign;
}

export class CampaignCategory implements ICampaignCategory {
  constructor(public id?: number, public name?: string, public influencerInfo?: IInfluencerInfo, public campaign?: ICampaign) {}
}
