import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IcdvIntranetTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ImportCertAndDeliVerifnDeleteDialogComponent } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn-delete-dialog.component';
import { ImportCertAndDeliVerifnService } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service';

describe('Component Tests', () => {
  describe('ImportCertAndDeliVerifn Management Delete Component', () => {
    let comp: ImportCertAndDeliVerifnDeleteDialogComponent;
    let fixture: ComponentFixture<ImportCertAndDeliVerifnDeleteDialogComponent>;
    let service: ImportCertAndDeliVerifnService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportCertAndDeliVerifnDeleteDialogComponent],
      })
        .overrideTemplate(ImportCertAndDeliVerifnDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImportCertAndDeliVerifnDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportCertAndDeliVerifnService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
