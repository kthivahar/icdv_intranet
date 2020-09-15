import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {IManufCostStmt, ManufCostStmt} from 'app/shared/model/manuf-cost-stmt.model';
import {Status} from 'app/shared/model/enumerations/status.model';
import {Observable} from "rxjs";
import {HttpResponse} from "@angular/common/http";
import {ManufCostStmtService} from "app/entities/manuf-cost-stmt/manuf-cost-stmt.service";
import {FormBuilder} from "@angular/forms";
@Component({
  selector: 'jhi-manuf-cost-stmt-detail',
  templateUrl: './manuf-cost-stmt-detail.component.html',
})
export class ManufCostStmtDetailComponent implements OnInit {
  manufCostStmt: IManufCostStmt | null = null;

  constructor(protected manufCostStmtService: ManufCostStmtService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufCostStmt }) => (this.manufCostStmt = manufCostStmt));
  }

  previousState(): void {
    window.history.back();
  }

  disableActions() : boolean{
    if(Status.OPEN === this.manufCostStmt?.status) {
      return false;
    }
    return true;
  }

  approveAction() : void {
    const iManufCostStmt = {
      ...new ManufCostStmt(),
      id: this.manufCostStmt?.id,
      nameOfManufacturer: this.manufCostStmt?.nameOfManufacturer,
      uniqueEntityNumber: this.manufCostStmt?.uniqueEntityNumber,
      ftaScheme: this.manufCostStmt?.ftaScheme,
      isQRVC: this.manufCostStmt?.isQRVC,
      isCTC: this.manufCostStmt?.isCTC,
      isMP: this.manufCostStmt?.isMP,
      descriptionOfGoods: this.manufCostStmt?.descriptionOfGoods,
      model: this.manufCostStmt?.model,
      hsCodeOfGoods: this.manufCostStmt?.hsCodeOfGoods,
      statementDate: this.manufCostStmt?.statementDate,
      incotermOfFta: this.manufCostStmt?.incotermOfFta,
      fobValueOfGoods: this.manufCostStmt?.fobValueOfGoods,
      noOfMCSUnits: this.manufCostStmt?.noOfMCSUnits,
      directLabourCost: this.manufCostStmt?.directLabourCost,
      directOverheadCost: this.manufCostStmt?.directOverheadCost,
      profit: this.manufCostStmt?.profit,
      otherCost: this.manufCostStmt?.otherCost,
      isDeclared1: this.manufCostStmt?.isDeclared1,
      declaringName1: this.manufCostStmt?.declaringName1,
      declarePosition1: this.manufCostStmt?.declarePosition1,
      declareOn1: this.manufCostStmt?.declareOn1,
      isDeclared2: this.manufCostStmt?.isDeclared2,
      declaringName2: this.manufCostStmt?.declaringName2,
      declarePosition2: this.manufCostStmt?.declarePosition2,
      declareOn2: this.manufCostStmt?.declareOn2,
      isDeclared3: this.manufCostStmt?.isDeclared3,
      declaringName3: this.manufCostStmt?.declaringName3,
      declarePosition3: this.manufCostStmt?.declarePosition3,
      declareOn3: this.manufCostStmt?.declareOn3,
      totalNonOrigMat: this.manufCostStmt?.totalNonOrigMat,
      totalOrigMat: this.manufCostStmt?.totalOrigMat,
      qvcRvc: this.manufCostStmt?.qvcRvc,
      status: Status.APPROVED,
      createdOn: this.manufCostStmt?.createdOn,
    };
    this.updateCostStmt(iManufCostStmt);
    alert("Successfully Approved");
  }

  rejectAction() : void {
    const iManufCostStmt  = {
      ...new ManufCostStmt(),
      id: this.manufCostStmt?.id,
      nameOfManufacturer: this.manufCostStmt?.nameOfManufacturer,
      uniqueEntityNumber: this.manufCostStmt?.uniqueEntityNumber,
      ftaScheme: this.manufCostStmt?.ftaScheme,
      isQRVC: this.manufCostStmt?.isQRVC,
      isCTC: this.manufCostStmt?.isCTC,
      isMP: this.manufCostStmt?.isMP,
      descriptionOfGoods: this.manufCostStmt?.descriptionOfGoods,
      model: this.manufCostStmt?.model,
      hsCodeOfGoods: this.manufCostStmt?.hsCodeOfGoods,
      statementDate: this.manufCostStmt?.statementDate,
      incotermOfFta: this.manufCostStmt?.incotermOfFta,
      fobValueOfGoods: this.manufCostStmt?.fobValueOfGoods,
      noOfMCSUnits: this.manufCostStmt?.noOfMCSUnits,
      directLabourCost: this.manufCostStmt?.directLabourCost,
      directOverheadCost: this.manufCostStmt?.directOverheadCost,
      profit: this.manufCostStmt?.profit,
      otherCost: this.manufCostStmt?.otherCost,
      isDeclared1: this.manufCostStmt?.isDeclared1,
      declaringName1: this.manufCostStmt?.declaringName1,
      declarePosition1: this.manufCostStmt?.declarePosition1,
      declareOn1: this.manufCostStmt?.declareOn1,
      isDeclared2: this.manufCostStmt?.isDeclared2,
      declaringName2: this.manufCostStmt?.declaringName2,
      declarePosition2: this.manufCostStmt?.declarePosition2,
      declareOn2: this.manufCostStmt?.declareOn2,
      isDeclared3: this.manufCostStmt?.isDeclared3,
      declaringName3: this.manufCostStmt?.declaringName3,
      declarePosition3: this.manufCostStmt?.declarePosition3,
      declareOn3: this.manufCostStmt?.declareOn3,
      totalNonOrigMat: this.manufCostStmt?.totalNonOrigMat,
      totalOrigMat: this.manufCostStmt?.totalOrigMat,
      qvcRvc: this.manufCostStmt?.qvcRvc,
      status: Status.REJECTED,
      createdOn: this.manufCostStmt?.createdOn,
    };
    this.updateCostStmt(iManufCostStmt);
    alert("Successfully rejected");
  }

  updateCostStmt(imanufactringCostStmt: IManufCostStmt): void {
    this.subscribeToSaveResponse(this.manufCostStmtService.update(imanufactringCostStmt));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ManufCostStmt>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
  }

}
