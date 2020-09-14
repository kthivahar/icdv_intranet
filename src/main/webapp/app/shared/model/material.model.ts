export interface IMaterial {
  id?: number;
  description?: string;
  hscode?: string;
  countryOfOrigin?: string;
  nameOfManufacturer?: string;
  valueOfMaterialsNonOriginating?: number;
  valueOfMaterialOriginating?: number;
}

export class Material implements IMaterial {
  constructor(
    public id?: number,
    public description?: string,
    public hscode?: string,
    public countryOfOrigin?: string,
    public nameOfManufacturer?: string,
    public valueOfMaterialsNonOriginating?: number,
    public valueOfMaterialOriginating?: number
  ) {}
}
