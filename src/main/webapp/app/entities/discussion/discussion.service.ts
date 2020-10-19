import axios from 'axios';

import { IDiscussion } from '@/shared/model/discussion.model';
import {IDiscussionThreads} from "@/shared/model/discussionThreads.model";
import {IChat} from "@/shared/model/chat.model";

const baseApiUrl = 'api/discussions';

export default class DiscussionService {
  public find(id: number): Promise<IDiscussion> {
    return new Promise<IDiscussion>((resolve, reject) => {
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

  public create(entity: IDiscussion): Promise<IDiscussion> {
    return new Promise<IDiscussion>((resolve, reject) => {
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

  public update(entity: IDiscussion): Promise<IDiscussion> {
    return new Promise<IDiscussion>((resolve, reject) => {
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

  public retrieveDiscussionByParticipant(userId: number): Promise<IDiscussionThreads[]>{
    return new Promise<IDiscussionThreads[]>((resolve, reject) => {
      axios
        .get('api/users/' + userId + '/discussions')
        .then( res => {
          resolve(res.data)
        })
        .catch(err => {
          reject(err)
        });
    });
  }

  public retrieveChatDiscussion(discussionId: number, userId:number): Promise<IChat> {
    return new Promise<IChat>((resolve, reject) => {
      axios.get(`${baseApiUrl}/${discussionId}/chat`,
        {
          params:{
        userId: userId
        }
        })
        .then ( res => {
          resolve(res.data)
        })
        .catch(err => {
          reject(err)
        });
    });
  }
}
