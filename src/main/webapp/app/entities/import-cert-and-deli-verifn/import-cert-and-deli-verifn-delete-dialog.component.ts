import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { ImportCertAndDeliVerifnService } from './import-cert-and-deli-verifn.service';

@Component({
  templateUrl: './import-cert-and-deli-verifn-delete-dialog.component.html',
})
export class ImportCertAndDeliVerifnDeleteDialogComponent {
  importCertAndDeliVerifn?: IImportCertAndDeliVerifn;

  constructor(
    protected importCertAndDeliVerifnService: ImportCertAndDeliVerifnService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.importCertAndDeliVerifnService.delete(id).subscribe(() => {
      this.eventManager.broadcast('importCertAndDeliVerifnListModification');
      this.activeModal.close();
    });
  }
}
