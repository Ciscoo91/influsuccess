import { IMessage } from '@/shared/model/message.model';
import {ICampaign} from "@/shared/model/campaign.model";

export interface IDiscussion {
  id?: number;
  participantIds?: number[];
  messages?: IMessage[];
  campaign?: ICampaign
}

export class Discussion implements IDiscussion {
  constructor(public id?: number, public participantIds?: number[], public messages?: IMessage[], public campaign?:ICampaign) {}
}
