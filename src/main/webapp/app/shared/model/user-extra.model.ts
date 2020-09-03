import { IUser } from '@/shared/model/user.model';

export const enum Role {
  ADMIN = 'ADMIN',
  INFLUENCER = 'INFLUENCER',
  OTHER = 'OTHER',
}

export interface IUserExtra {
  id?: number;
  country?: string;
  birthday?: Date;
  phone?: number;
  role?: Role;
  user?: IUser;
}

export class UserExtra implements IUserExtra {
  constructor(
    public id?: number,
    public country?: string,
    public birthday?: Date,
    public phone?: number,
    public role?: Role,
    public user?: IUser
  ) {}
}
