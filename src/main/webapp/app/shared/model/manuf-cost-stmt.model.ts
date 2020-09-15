import { Moment } from 'moment';
import { IMaterial } from 'app/shared/model/material.model';
import { IContent } from 'app/shared/model/content.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IManufCostStmt {
  id?: number;
  nameOfManufacturer?: string;
  uniqueEntityNumber?: string;
  ftaScheme?: string;
  isQRVC?: boolean;
  isCTC?: boolean;
  isMP?: boolean;
  descriptionOfGoods?: string;
  model?: string;
  hsCodeOfGoods?: string;
  statementDate?: Moment;
  incotermOfFta?: string;
  fobValueOfGoods?: number;
  noOfMCSUnits?: number;
  directLabourCost?: number;
  directOverheadCost?: number;
  profit?: number;
  otherCost?: number;
  isDeclared1?: boolean;
  declaringName1?: string;
  declarePosition1?: string;
  declareOn1?: Moment;
  isDeclared2?: boolean;
  declaringName2?: string;
  declarePosition2?: string;
  declareOn2?: Moment;
  isDeclared3?: boolean;
  declaringName3?: string;
  declarePosition3?: string;
  declareOn3?: Moment;
  totalNonOrigMat?: number;
  totalOrigMat?: number;
  qvcRvc?: number;
  status?: Status;
  createdOn?: Moment;
  materials?: IMaterial[];
  contents?: IContent[];
}

export class ManufCostStmt implements IManufCostStmt {
  constructor(
    public id?: number,
    public nameOfManufacturer?: string,
    public uniqueEntityNumber?: string,
    public ftaScheme?: string,
    public isQRVC?: boolean,
    public isCTC?: boolean,
    public isMP?: boolean,
    public descriptionOfGoods?: string,
    public model?: string,
    public hsCodeOfGoods?: string,
    public statementDate?: Moment,
    public incotermOfFta?: string,
    public fobValueOfGoods?: number,
    public noOfMCSUnits?: number,
    public directLabourCost?: number,
    public directOverheadCost?: number,
    public profit?: number,
    public otherCost?: number,
    public isDeclared1?: boolean,
    public declaringName1?: string,
    public declarePosition1?: string,
    public declareOn1?: Moment,
    public isDeclared2?: boolean,
    public declaringName2?: string,
    public declarePosition2?: string,
    public declareOn2?: Moment,
    public isDeclared3?: boolean,
    public declaringName3?: string,
    public declarePosition3?: string,
    public declareOn3?: Moment,
    public totalNonOrigMat?: number,
    public totalOrigMat?: number,
    public qvcRvc?: number,
    public status?: Status,
    public createdOn?: Moment,
    public materials?: IMaterial[],
    public contents?: IContent[]
  ) {
    this.isQRVC = this.isQRVC || false;
    this.isCTC = this.isCTC || false;
    this.isMP = this.isMP || false;
    this.isDeclared1 = this.isDeclared1 || false;
    this.isDeclared2 = this.isDeclared2 || false;
    this.isDeclared3 = this.isDeclared3 || false;
  }
}
