import axios from 'axios';

import { IMessage, MessageChat } from '@/shared/model/message.model';

const baseApiUrl = 'api/messages';

export default class MessageService {
  public find(id: number): Promise<IMessage> {
    return new Promise<IMessage>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IMessage): Promise<IMessage> {
    return new Promise<IMessage>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IMessage): Promise<IMessage> {
    return new Promise<IMessage>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getCountNewMessages(userId: number, campaignId: number): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}` + '/countNew', {
          params: {
            'userId': userId,
            'campaignId': campaignId,
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public getAllNewMessageCount(userId: number): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/alllNewMessageCount`, {
          params: {
            'userId': userId,
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public saveMessageChat(entity: MessageChat, selectedDiscussionId: number) {
    return new Promise<MessageChat>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/messageChat`, entity, {
          params: {
            discussionId: selectedDiscussionId,
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
