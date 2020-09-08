import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {IImportCertAndDeliVerifn, ImportCertAndDeliVerifn} from 'app/shared/model/import-cert-and-deli-verifn.model';
import {Status} from 'app/shared/model/enumerations/status.model';
import {ImportCertAndDeliVerifnService} from "app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service";
import {Observable} from "rxjs";
import {HttpResponse} from "@angular/common/http";


@Component({
  selector: 'jhi-import-cert-and-deli-verifn-detail',
  templateUrl: './import-cert-and-deli-verifn-detail.component.html',
})
export class ImportCertAndDeliVerifnDetailComponent implements OnInit {
  importCertAndDeliVerifn: IImportCertAndDeliVerifn | null = null;

  constructor(protected activatedRoute: ActivatedRoute,
              protected importCertAndDeliVerifnService: ImportCertAndDeliVerifnService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importCertAndDeliVerifn }) => (this.importCertAndDeliVerifn = importCertAndDeliVerifn));
  }

  previousState(): void {
    window.history.back();
  }

  disableActions(): boolean {
    if(Status.OPEN === this.importCertAndDeliVerifn?.status) {
      return false;
    }
    return true;
  }

  approveAction(): void {
    const imanufactringCostStmt  = {
      ...new ImportCertAndDeliVerifn(),
      id: this.importCertAndDeliVerifn?.id,
      externalId: this.importCertAndDeliVerifn?.externalId,
      companyName: this.importCertAndDeliVerifn?.companyName,
      uniqueEntityNumberUen: this.importCertAndDeliVerifn?.uniqueEntityNumberUen,
      address: this.importCertAndDeliVerifn?.address,
      nameOfContactPerson: this.importCertAndDeliVerifn?.nameOfContactPerson,
      designation: this.importCertAndDeliVerifn?.designation,
      contactNo: this.importCertAndDeliVerifn?.contactNo,
      email: this.importCertAndDeliVerifn?.email,
      exporterCompanyName: this.importCertAndDeliVerifn?.exporterCompanyName,
      exporterAddress: this.importCertAndDeliVerifn?.exporterAddress,
      euCompanyName: this.importCertAndDeliVerifn?.euCompanyName,
      country: this.importCertAndDeliVerifn?.country,
      radio1: this.importCertAndDeliVerifn?.radio1,
      status: Status.APPROVED,
      createdOn: this.importCertAndDeliVerifn?.createdOn,
    };

    this.updateCostStmt(imanufactringCostStmt);
    alert("Successfully approved");
  }

  rejectAction(): void {
    const imanufactringCostStmt  = {
      ...new ImportCertAndDeliVerifn(),
      id: this.importCertAndDeliVerifn?.id,
      externalId: this.importCertAndDeliVerifn?.externalId,
      companyName: this.importCertAndDeliVerifn?.companyName,
      uniqueEntityNumberUen: this.importCertAndDeliVerifn?.uniqueEntityNumberUen,
      address: this.importCertAndDeliVerifn?.address,
      nameOfContactPerson: this.importCertAndDeliVerifn?.nameOfContactPerson,
      designation: this.importCertAndDeliVerifn?.designation,
      contactNo: this.importCertAndDeliVerifn?.contactNo,
      email: this.importCertAndDeliVerifn?.email,
      exporterCompanyName: this.importCertAndDeliVerifn?.exporterCompanyName,
      exporterAddress: this.importCertAndDeliVerifn?.exporterAddress,
      euCompanyName: this.importCertAndDeliVerifn?.euCompanyName,
      country: this.importCertAndDeliVerifn?.country,
      radio1: this.importCertAndDeliVerifn?.radio1,
      status: Status.REJECTED,
      createdOn: this.importCertAndDeliVerifn?.createdOn,
    };
    this.updateCostStmt(imanufactringCostStmt)
    alert("Successfully rejected" +
      "")
  }

  updateCostStmt(imanufactringCostStmt: IImportCertAndDeliVerifn): void {
    this.subscribeToSaveResponse(this.importCertAndDeliVerifnService.update(imanufactringCostStmt));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImportCertAndDeliVerifn>>): void {
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
