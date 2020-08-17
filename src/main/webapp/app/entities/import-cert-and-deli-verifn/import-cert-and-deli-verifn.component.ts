import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { ImportCertAndDeliVerifnService } from './import-cert-and-deli-verifn.service';
import { ImportCertAndDeliVerifnDeleteDialogComponent } from './import-cert-and-deli-verifn-delete-dialog.component';

@Component({
  selector: 'jhi-import-cert-and-deli-verifn',
  templateUrl: './import-cert-and-deli-verifn.component.html',
})
export class ImportCertAndDeliVerifnComponent implements OnInit, OnDestroy {
  importCertAndDeliVerifns?: IImportCertAndDeliVerifn[];
  eventSubscriber?: Subscription;

  constructor(
    protected importCertAndDeliVerifnService: ImportCertAndDeliVerifnService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.importCertAndDeliVerifnService
      .query()
      .subscribe((res: HttpResponse<IImportCertAndDeliVerifn[]>) => (this.importCertAndDeliVerifns = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInImportCertAndDeliVerifns();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IImportCertAndDeliVerifn): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInImportCertAndDeliVerifns(): void {
    this.eventSubscriber = this.eventManager.subscribe('importCertAndDeliVerifnListModification', () => this.loadAll());
  }

  delete(importCertAndDeliVerifn: IImportCertAndDeliVerifn): void {
    const modalRef = this.modalService.open(ImportCertAndDeliVerifnDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.importCertAndDeliVerifn = importCertAndDeliVerifn;
  }
}
