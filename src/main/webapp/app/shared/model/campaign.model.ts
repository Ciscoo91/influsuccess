import { ICampaignCategory } from '@/shared/model/campaign-category.model';
import { ISocialNetwork } from '@/shared/model/social-network.model';
import { IUser } from '@/shared/model/user.model';

export const enum LangKey {
  AR = 'AR',
  FR = 'FR',
  EN = 'EN',
  ES = 'ES',
  IT = 'IT',
  DE = 'DE',
}

export const enum CampaignStatus {
  CLOSED = 'CLOSED',
  OPENED = 'OPENED',
}

export interface ICampaign {
  id?: number;
  langKey?: LangKey;
  title?: string;
  description?: string;
  status?: CampaignStatus;
  minFollowers?: number;
  maxFollowers?: number;
  targetCountries?: string;
  categories?: ICampaignCategory[];
  socialNetworks?: ISocialNetwork[];
  user?: IUser;
}

export class Campaign implements ICampaign {
  constructor(
    public id?: number,
    public langKey?: LangKey,
    public title?: string,
    public description?: string,
    public status?: CampaignStatus,
    public minFollowers?: number,
    public maxFollowers?: number,
    public targetCountries?: string,
    public categories?: ICampaignCategory[],
    public socialNetworks?: ISocialNetwork[],
    public user?: IUser
  ) {}
}
