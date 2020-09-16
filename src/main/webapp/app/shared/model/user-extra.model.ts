import { IUser } from '@/shared/model/user.model';

export interface IUserExtra {
  id?: number;
  country?: string;
  birthday?: Date;
  phone?: number;
}

export class UserExtra implements IUserExtra {
  constructor(
    public id?: number,
    public country?: string,
    public birthday?: Date,
    public phone?: number,
  ) {}
}
