export class SearchForm {
  constructor(
    public username?: string,
    public socialNetworkName?: string,
    public campaignCategoryEnum?: string,
    public countryCode?: string,
    private totalFollowersMin?: number
  ) {}
}
