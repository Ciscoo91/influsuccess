export enum CampaignCategoryEnum {
  Healthy,
  Sport,
  Travel,
  Lifestyle,
  Parenting,
  Luxury,
  Pet,
}

export interface ICampaignCategory {
  name?: CampaignCategoryEnum;
}

export class CampaignCategory implements ICampaignCategory {
  constructor(public name?: CampaignCategoryEnum) {}
}
