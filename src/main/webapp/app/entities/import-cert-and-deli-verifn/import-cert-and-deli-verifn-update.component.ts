import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IImportCertAndDeliVerifn, ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { ImportCertAndDeliVerifnService } from './import-cert-and-deli-verifn.service';

@Component({
  selector: 'jhi-import-cert-and-deli-verifn-update',
  templateUrl: './import-cert-and-deli-verifn-update.component.html',
})
export class ImportCertAndDeliVerifnUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    externalId: [null, [Validators.required]],
    companyName: [null, [Validators.required, Validators.maxLength(100)]],
    uniqueEntityNumberUen: [null, [Validators.required]],
    address: [],
    nameOfContactPerson: [],
    designation: [],
    contactNo: [],
    email: [],
    exporterCompanyName: [],
    exporterAddress: [],
    euCompanyName: [],
    country: [],
    radio1: [],
    status: [],
  });

  constructor(
    protected importCertAndDeliVerifnService: ImportCertAndDeliVerifnService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importCertAndDeliVerifn }) => {
      this.updateForm(importCertAndDeliVerifn);
    });
  }

  updateForm(importCertAndDeliVerifn: IImportCertAndDeliVerifn): void {
    this.editForm.patchValue({
      id: importCertAndDeliVerifn.id,
      externalId: importCertAndDeliVerifn.externalId,
      companyName: importCertAndDeliVerifn.companyName,
      uniqueEntityNumberUen: importCertAndDeliVerifn.uniqueEntityNumberUen,
      address: importCertAndDeliVerifn.address,
      nameOfContactPerson: importCertAndDeliVerifn.nameOfContactPerson,
      designation: importCertAndDeliVerifn.designation,
      contactNo: importCertAndDeliVerifn.contactNo,
      email: importCertAndDeliVerifn.email,
      exporterCompanyName: importCertAndDeliVerifn.exporterCompanyName,
      exporterAddress: importCertAndDeliVerifn.exporterAddress,
      euCompanyName: importCertAndDeliVerifn.euCompanyName,
      country: importCertAndDeliVerifn.country,
      radio1: importCertAndDeliVerifn.radio1,
      status: importCertAndDeliVerifn.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const importCertAndDeliVerifn = this.createFromForm();
    if (importCertAndDeliVerifn.id !== undefined) {
      this.subscribeToSaveResponse(this.importCertAndDeliVerifnService.update(importCertAndDeliVerifn));
    } else {
      this.subscribeToSaveResponse(this.importCertAndDeliVerifnService.create(importCertAndDeliVerifn));
    }
  }

  private createFromForm(): IImportCertAndDeliVerifn {
    return {
      ...new ImportCertAndDeliVerifn(),
      id: this.editForm.get(['id'])!.value,
      externalId: this.editForm.get(['externalId'])!.value,
      companyName: this.editForm.get(['companyName'])!.value,
      uniqueEntityNumberUen: this.editForm.get(['uniqueEntityNumberUen'])!.value,
      address: this.editForm.get(['address'])!.value,
      nameOfContactPerson: this.editForm.get(['nameOfContactPerson'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      contactNo: this.editForm.get(['contactNo'])!.value,
      email: this.editForm.get(['email'])!.value,
      exporterCompanyName: this.editForm.get(['exporterCompanyName'])!.value,
      exporterAddress: this.editForm.get(['exporterAddress'])!.value,
      euCompanyName: this.editForm.get(['euCompanyName'])!.value,
      country: this.editForm.get(['country'])!.value,
      radio1: this.editForm.get(['radio1'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImportCertAndDeliVerifn>>): void {
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
