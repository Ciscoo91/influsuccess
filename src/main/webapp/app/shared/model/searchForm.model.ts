export class influencerSearchDTO {
  constructor(
    public username?: string,
    public socialNetworkName?: string,
    public campaignCategoryEnum?: string,
    public countryCode?: string,
    public totalFollowersMin?: number
  ) {}
}
