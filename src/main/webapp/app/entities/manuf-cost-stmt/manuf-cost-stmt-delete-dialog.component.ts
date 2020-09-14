import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';
import { ManufCostStmtService } from './manuf-cost-stmt.service';

@Component({
  templateUrl: './manuf-cost-stmt-delete-dialog.component.html',
})
export class ManufCostStmtDeleteDialogComponent {
  manufCostStmt?: IManufCostStmt;

  constructor(
    protected manufCostStmtService: ManufCostStmtService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.manufCostStmtService.delete(id).subscribe(() => {
      this.eventManager.broadcast('manufCostStmtListModification');
      this.activeModal.close();
    });
  }
}
