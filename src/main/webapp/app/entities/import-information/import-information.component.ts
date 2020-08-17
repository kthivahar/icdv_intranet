import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IImportInformation } from 'app/shared/model/import-information.model';
import { ImportInformationService } from './import-information.service';
import { ImportInformationDeleteDialogComponent } from './import-information-delete-dialog.component';

@Component({
  selector: 'jhi-import-information',
  templateUrl: './import-information.component.html',
})
export class ImportInformationComponent implements OnInit, OnDestroy {
  importInformations?: IImportInformation[];
  eventSubscriber?: Subscription;

  constructor(
    protected importInformationService: ImportInformationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.importInformationService
      .query()
      .subscribe((res: HttpResponse<IImportInformation[]>) => (this.importInformations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInImportInformations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IImportInformation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInImportInformations(): void {
    this.eventSubscriber = this.eventManager.subscribe('importInformationListModification', () => this.loadAll());
  }

  delete(importInformation: IImportInformation): void {
    const modalRef = this.modalService.open(ImportInformationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.importInformation = importInformation;
  }
}
