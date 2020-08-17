import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IImportInformation, ImportInformation } from 'app/shared/model/import-information.model';
import { ImportInformationService } from './import-information.service';
import { IImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { ImportCertAndDeliVerifnService } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service';

@Component({
  selector: 'jhi-import-information-update',
  templateUrl: './import-information-update.component.html',
})
export class ImportInformationUpdateComponent implements OnInit {
  isSaving = false;
  importcertanddeliverifns: IImportCertAndDeliVerifn[] = [];

  editForm = this.fb.group({
    id: [],
    externalId: [],
    descriptionOfGoods: [],
    hsCode: [],
    unit: [],
    quantity: [],
    value: [],
    importCertAndDeliVerifn: [],
  });

  constructor(
    protected importInformationService: ImportInformationService,
    protected importCertAndDeliVerifnService: ImportCertAndDeliVerifnService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importInformation }) => {
      this.updateForm(importInformation);

      this.importCertAndDeliVerifnService
        .query()
        .subscribe((res: HttpResponse<IImportCertAndDeliVerifn[]>) => (this.importcertanddeliverifns = res.body || []));
    });
  }

  updateForm(importInformation: IImportInformation): void {
    this.editForm.patchValue({
      id: importInformation.id,
      externalId: importInformation.externalId,
      descriptionOfGoods: importInformation.descriptionOfGoods,
      hsCode: importInformation.hsCode,
      unit: importInformation.unit,
      quantity: importInformation.quantity,
      value: importInformation.value,
      importCertAndDeliVerifn: importInformation.importCertAndDeliVerifn,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const importInformation = this.createFromForm();
    if (importInformation.id !== undefined) {
      this.subscribeToSaveResponse(this.importInformationService.update(importInformation));
    } else {
      this.subscribeToSaveResponse(this.importInformationService.create(importInformation));
    }
  }

  private createFromForm(): IImportInformation {
    return {
      ...new ImportInformation(),
      id: this.editForm.get(['id'])!.value,
      externalId: this.editForm.get(['externalId'])!.value,
      descriptionOfGoods: this.editForm.get(['descriptionOfGoods'])!.value,
      hsCode: this.editForm.get(['hsCode'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      value: this.editForm.get(['value'])!.value,
      importCertAndDeliVerifn: this.editForm.get(['importCertAndDeliVerifn'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImportInformation>>): void {
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

  trackById(index: number, item: IImportCertAndDeliVerifn): any {
    return item.id;
  }
}
