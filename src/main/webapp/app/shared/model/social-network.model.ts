export enum SocialNetworkEnum {
  Instagram,
  Tiktok,
  Facebook,
  Snapchat,
  Youtube,
  Linkedin,
  Twitter,
  Blog,
  Pinterest,
}
export interface ISocialNetwork {
  name?: SocialNetworkEnum;
}

export class SocialNetwork implements ISocialNetwork {
  constructor(public name?: SocialNetworkEnum) {}
}
