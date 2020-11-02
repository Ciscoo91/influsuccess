import { ParticipantChat } from '@/shared/model/user.model';
import { MessageChat } from '@/shared/model/message.model';

export interface IChat {
  chatTitle?: string;
  participants?: ParticipantChat[];
  messages?: MessageChat[];
  discussionId?: number;
}

export class Chat implements IChat {
  constructor(
    public chatTitle?: string,
    public participants?: ParticipantChat[],
    public messages?: MessageChat[],
    public discussionId?: number
  ) {}
}
