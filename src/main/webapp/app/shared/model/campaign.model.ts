import { ICampaignCategory } from '@/shared/model/campaign-category.model';
import { ISocialNetwork } from '@/shared/model/social-network.model';

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

export const enum Sort {
  ASC = 'asc',
  DESC = 'desc',
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
  userId?: number;
  userLogin?: string;
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
    public userId?: number,
    public userLogin?: string
  ) {}
}

export class CampaignFilter {
  constructor(public title?: string, public userLogin?: string, public status?: CampaignStatus) {}
}
