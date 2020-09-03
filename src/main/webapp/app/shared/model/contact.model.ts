import { IUser } from '@/shared/model/user.model';
import { ICampaign } from '@/shared/model/campaign.model';
import { IInstagInfluencer } from '@/shared/model/instag-influencer.model';

export interface IContact {
  id?: number;
  user?: IUser;
  compaign?: ICampaign;
  instagInfluencer?: IInstagInfluencer;
}

export class Contact implements IContact {
  constructor(public id?: number, public user?: IUser, public compaign?: ICampaign, public instagInfluencer?: IInstagInfluencer) {}
}
