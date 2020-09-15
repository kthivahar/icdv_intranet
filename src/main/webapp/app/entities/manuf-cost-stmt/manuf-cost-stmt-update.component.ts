import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IManufCostStmt, ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';
import { ManufCostStmtService } from './manuf-cost-stmt.service';

@Component({
  selector: 'jhi-manuf-cost-stmt-update',
  templateUrl: './manuf-cost-stmt-update.component.html',
})
export class ManufCostStmtUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameOfManufacturer: [],
    uniqueEntityNumber: [],
    ftaScheme: [],
    isQRVC: [],
    isCTC: [],
    isMP: [],
    descriptionOfGoods: [],
    model: [],
    hsCodeOfGoods: [],
    statementDate: [],
    incotermOfFta: [],
    fobValueOfGoods: [],
    noOfMCSUnits: [],
    directLabourCost: [],
    directOverheadCost: [],
    profit: [],
    otherCost: [],
    isDeclared1: [],
    declaringName1: [],
    declarePosition1: [],
    declareOn1: [],
    isDeclared2: [],
    declaringName2: [],
    declarePosition2: [],
    declareOn2: [],
    isDeclared3: [],
    declaringName3: [],
    declarePosition3: [],
    declareOn3: [],
    totalNonOrigMat: [],
    totalOrigMat: [],
    qvcRvc: [],
    status: [],
    createdOn: [],
  });

  constructor(protected manufCostStmtService: ManufCostStmtService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufCostStmt }) => {
      if (!manufCostStmt.id) {
        const today = moment().startOf('day');
        manufCostStmt.statementDate = today;
        manufCostStmt.declareOn1 = today;
        manufCostStmt.declareOn2 = today;
        manufCostStmt.declareOn3 = today;
        manufCostStmt.createdOn = today;
      }

      this.updateForm(manufCostStmt);
    });
  }

  updateForm(manufCostStmt: IManufCostStmt): void {
    this.editForm.patchValue({
      id: manufCostStmt.id,
      nameOfManufacturer: manufCostStmt.nameOfManufacturer,
      uniqueEntityNumber: manufCostStmt.uniqueEntityNumber,
      ftaScheme: manufCostStmt.ftaScheme,
      isQRVC: manufCostStmt.isQRVC,
      isCTC: manufCostStmt.isCTC,
      isMP: manufCostStmt.isMP,
      descriptionOfGoods: manufCostStmt.descriptionOfGoods,
      model: manufCostStmt.model,
      hsCodeOfGoods: manufCostStmt.hsCodeOfGoods,
      statementDate: manufCostStmt.statementDate ? manufCostStmt.statementDate.format(DATE_TIME_FORMAT) : null,
      incotermOfFta: manufCostStmt.incotermOfFta,
      fobValueOfGoods: manufCostStmt.fobValueOfGoods,
      noOfMCSUnits: manufCostStmt.noOfMCSUnits,
      directLabourCost: manufCostStmt.directLabourCost,
      directOverheadCost: manufCostStmt.directOverheadCost,
      profit: manufCostStmt.profit,
      otherCost: manufCostStmt.otherCost,
      isDeclared1: manufCostStmt.isDeclared1,
      declaringName1: manufCostStmt.declaringName1,
      declarePosition1: manufCostStmt.declarePosition1,
      declareOn1: manufCostStmt.declareOn1 ? manufCostStmt.declareOn1.format(DATE_TIME_FORMAT) : null,
      isDeclared2: manufCostStmt.isDeclared2,
      declaringName2: manufCostStmt.declaringName2,
      declarePosition2: manufCostStmt.declarePosition2,
      declareOn2: manufCostStmt.declareOn2 ? manufCostStmt.declareOn2.format(DATE_TIME_FORMAT) : null,
      isDeclared3: manufCostStmt.isDeclared3,
      declaringName3: manufCostStmt.declaringName3,
      declarePosition3: manufCostStmt.declarePosition3,
      declareOn3: manufCostStmt.declareOn3 ? manufCostStmt.declareOn3.format(DATE_TIME_FORMAT) : null,
      totalNonOrigMat: manufCostStmt.totalNonOrigMat,
      totalOrigMat: manufCostStmt.totalOrigMat,
      qvcRvc: manufCostStmt.qvcRvc,
      status: manufCostStmt.status,
      createdOn: manufCostStmt.createdOn ? manufCostStmt.createdOn.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const manufCostStmt = this.createFromForm();
    if (manufCostStmt.id !== undefined) {
      this.subscribeToSaveResponse(this.manufCostStmtService.update(manufCostStmt));
    } else {
      this.subscribeToSaveResponse(this.manufCostStmtService.create(manufCostStmt));
    }
  }

  private createFromForm(): IManufCostStmt {
    return {
      ...new ManufCostStmt(),
      id: this.editForm.get(['id'])!.value,
      nameOfManufacturer: this.editForm.get(['nameOfManufacturer'])!.value,
      uniqueEntityNumber: this.editForm.get(['uniqueEntityNumber'])!.value,
      ftaScheme: this.editForm.get(['ftaScheme'])!.value,
      isQRVC: this.editForm.get(['isQRVC'])!.value,
      isCTC: this.editForm.get(['isCTC'])!.value,
      isMP: this.editForm.get(['isMP'])!.value,
      descriptionOfGoods: this.editForm.get(['descriptionOfGoods'])!.value,
      model: this.editForm.get(['model'])!.value,
      hsCodeOfGoods: this.editForm.get(['hsCodeOfGoods'])!.value,
      statementDate: this.editForm.get(['statementDate'])!.value
        ? moment(this.editForm.get(['statementDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      incotermOfFta: this.editForm.get(['incotermOfFta'])!.value,
      fobValueOfGoods: this.editForm.get(['fobValueOfGoods'])!.value,
      noOfMCSUnits: this.editForm.get(['noOfMCSUnits'])!.value,
      directLabourCost: this.editForm.get(['directLabourCost'])!.value,
      directOverheadCost: this.editForm.get(['directOverheadCost'])!.value,
      profit: this.editForm.get(['profit'])!.value,
      otherCost: this.editForm.get(['otherCost'])!.value,
      isDeclared1: this.editForm.get(['isDeclared1'])!.value,
      declaringName1: this.editForm.get(['declaringName1'])!.value,
      declarePosition1: this.editForm.get(['declarePosition1'])!.value,
      declareOn1: this.editForm.get(['declareOn1'])!.value ? moment(this.editForm.get(['declareOn1'])!.value, DATE_TIME_FORMAT) : undefined,
      isDeclared2: this.editForm.get(['isDeclared2'])!.value,
      declaringName2: this.editForm.get(['declaringName2'])!.value,
      declarePosition2: this.editForm.get(['declarePosition2'])!.value,
      declareOn2: this.editForm.get(['declareOn2'])!.value ? moment(this.editForm.get(['declareOn2'])!.value, DATE_TIME_FORMAT) : undefined,
      isDeclared3: this.editForm.get(['isDeclared3'])!.value,
      declaringName3: this.editForm.get(['declaringName3'])!.value,
      declarePosition3: this.editForm.get(['declarePosition3'])!.value,
      declareOn3: this.editForm.get(['declareOn3'])!.value ? moment(this.editForm.get(['declareOn3'])!.value, DATE_TIME_FORMAT) : undefined,
      totalNonOrigMat: this.editForm.get(['totalNonOrigMat'])!.value,
      totalOrigMat: this.editForm.get(['totalOrigMat'])!.value,
      qvcRvc: this.editForm.get(['qvcRvc'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManufCostStmt>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
