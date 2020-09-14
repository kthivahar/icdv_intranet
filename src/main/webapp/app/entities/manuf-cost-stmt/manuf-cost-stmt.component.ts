import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';
import { ManufCostStmtService } from './manuf-cost-stmt.service';
import { ManufCostStmtDeleteDialogComponent } from './manuf-cost-stmt-delete-dialog.component';

@Component({
  selector: 'jhi-manuf-cost-stmt',
  templateUrl: './manuf-cost-stmt.component.html',
})
export class ManufCostStmtComponent implements OnInit, OnDestroy {
  manufCostStmts?: IManufCostStmt[];
  eventSubscriber?: Subscription;

  constructor(
    protected manufCostStmtService: ManufCostStmtService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.manufCostStmtService.query().subscribe((res: HttpResponse<IManufCostStmt[]>) => (this.manufCostStmts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInManufCostStmts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IManufCostStmt): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInManufCostStmts(): void {
    this.eventSubscriber = this.eventManager.subscribe('manufCostStmtListModification', () => this.loadAll());
  }

  delete(manufCostStmt: IManufCostStmt): void {
    const modalRef = this.modalService.open(ManufCostStmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.manufCostStmt = manufCostStmt;
  }
}
