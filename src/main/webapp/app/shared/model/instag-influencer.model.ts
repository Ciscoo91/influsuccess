export interface IInstagInfluencer {
  id?: number;
  url?: string;
}

export class InstagInfluencer implements IInstagInfluencer {
  constructor(public id?: number, public url?: string) {}
}
