export interface IContent {
  id?: number;
  name?: string;
  url?: string;
  orgName?: string;
  bucketName?: string;
  objectKey?: string;
}

export class Content implements IContent {
  constructor(
    public id?: number,
    public name?: string,
    public url?: string,
    public orgName?: string,
    public bucketName?: string,
    public objectKey?: string
  ) {}
}
