import { Moment } from 'moment';
import { IImportInformation } from 'app/shared/model/import-information.model';
import { IContent } from 'app/shared/model/content.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IImportCertAndDeliVerifn {
  id?: number;
  externalId?: string;
  companyName?: string;
  uniqueEntityNumberUen?: string;
  address?: string;
  nameOfContactPerson?: string;
  designation?: string;
  contactNo?: string;
  email?: string;
  exporterCompanyName?: string;
  exporterAddress?: string;
  euCompanyName?: string;
  country?: string;
  radio1?: string;
  status?: Status;
  createdOn?: Moment;
  importInformations?: IImportInformation[];
  contents?: IContent[];
}

export class ImportCertAndDeliVerifn implements IImportCertAndDeliVerifn {
  constructor(
    public id?: number,
    public externalId?: string,
    public companyName?: string,
    public uniqueEntityNumberUen?: string,
    public address?: string,
    public nameOfContactPerson?: string,
    public designation?: string,
    public contactNo?: string,
    public email?: string,
    public exporterCompanyName?: string,
    public exporterAddress?: string,
    public euCompanyName?: string,
    public country?: string,
    public radio1?: string,
    public status?: Status,
    public createdOn?: Moment,
    public importInformations?: IImportInformation[],
    public contents?: IContent[]
  ) {}
}
