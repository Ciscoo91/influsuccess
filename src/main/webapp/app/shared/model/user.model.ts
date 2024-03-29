export interface IUser {
  id?: any;
  login?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: any[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: any[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string
  ) {}
}

export class ParticipantChat {
  constructor(public id?: number, public name?: string) {}
}

export class UserFilter {
  constructor(public login?: string, public email?: string, public firstName?: string, public lastName?: string) {}
}

export class MailUser {
  constructor(
    public login?: string,
    public userEmail?: string,
    public adminEmail?: string,
    public content?: string,
    public langKey?: string
  ) {}
}
