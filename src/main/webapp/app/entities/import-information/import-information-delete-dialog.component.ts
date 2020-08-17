import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImportInformation } from 'app/shared/model/import-information.model';
import { ImportInformationService } from './import-information.service';

@Component({
  templateUrl: './import-information-delete-dialog.component.html',
})
export class ImportInformationDeleteDialogComponent {
  importInformation?: IImportInformation;

  constructor(
    protected importInformationService: ImportInformationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.importInformationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('importInformationListModification');
      this.activeModal.close();
    });
  }
}
