export interface IDiscussionThreads {
  discussionId: number;
  campaignId: number;
  campaignTitle: string;
  loginParticipants: string[];
  lastMessage: string;
  countNewMessage?:number;
}

export class DiscussionThreads implements IDiscussionThreads {
  constructor(public discussionId: number, public campaignId: number, public campaignTitle: string, public loginParticipants: string[], public lastMessage, public countNewMessages?: number) {}
}
