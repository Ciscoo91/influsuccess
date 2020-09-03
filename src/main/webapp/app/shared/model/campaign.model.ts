import { IUser } from '@/shared/model/user.model';

export interface ICampaign {
  id?: number;
  title?: string;
  user?: IUser;
}

export class Campaign implements ICampaign {
  constructor(public id?: number, public title?: string, public user?: IUser) {}
}
