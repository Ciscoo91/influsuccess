import { IUser } from '@/shared/model/user.model';
import { IMessage } from '@/shared/model/message.model';

export interface IDiscussion {
  id?: number;
  participants?: IUser[];
  messages?: IMessage[];
}

export class Discussion implements IDiscussion {
  constructor(public id?: number, public participants?: IUser[], public messages?: IMessage[]) {}
}
