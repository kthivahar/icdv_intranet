import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImportInformation } from 'app/shared/model/import-information.model';

@Component({
  selector: 'jhi-import-information-detail',
  templateUrl: './import-information-detail.component.html',
})
export class ImportInformationDetailComponent implements OnInit {
  importInformation: IImportInformation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ importInformation }) => (this.importInformation = importInformation));
  }

  previousState(): void {
    window.history.back();
  }
}
