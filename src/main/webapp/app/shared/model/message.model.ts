import { IUser } from '@/shared/model/user.model';
import { IDiscussion } from '@/shared/model/discussion.model';

export const enum MessageStatus {
  SENT = 'SENT',
  DISTRIBUTED = 'DISTRIBUTED',
  OPENED = 'OPENED',
}

export interface IMessage {
  id?: number;
  status?: MessageStatus;
  content?: string;
  sender?: IUser;
  receiver?: IUser;
  discussion?: IDiscussion;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public status?: MessageStatus,
    public content?: string,
    public sender?: IUser,
    public receiver?: IUser,
    public discussion?: IDiscussion
  ) {}
}

export class MessageChat {
  constructor(
    public content: string,
    public myself: boolean,
    public participantId: number,
    public timestamp: Object,
    public discussionId: number
  ) {}
}
