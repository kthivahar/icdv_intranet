import { IImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';

export interface IImportInformation {
  id?: number;
  externalId?: string;
  descriptionOfGoods?: string;
  hsCode?: string;
  unit?: string;
  quantity?: number;
  value?: number;
  importCertAndDeliVerifn?: IImportCertAndDeliVerifn;
}

export class ImportInformation implements IImportInformation {
  constructor(
    public id?: number,
    public externalId?: string,
    public descriptionOfGoods?: string,
    public hsCode?: string,
    public unit?: string,
    public quantity?: number,
    public value?: number,
    public importCertAndDeliVerifn?: IImportCertAndDeliVerifn
  ) {}
}
