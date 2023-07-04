export interface ICountry {
  code?: string;
  name?: string;
}

export class Country implements ICountry {
  constructor(public code?: string, public name?: string) {}
}
