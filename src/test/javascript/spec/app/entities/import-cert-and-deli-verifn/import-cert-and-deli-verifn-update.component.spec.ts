import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportCertAndDeliVerifnUpdateComponent } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn-update.component';
import { ImportCertAndDeliVerifnService } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service';
import { ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';

describe('Component Tests', () => {
  describe('ImportCertAndDeliVerifn Management Update Component', () => {
    let comp: ImportCertAndDeliVerifnUpdateComponent;
    let fixture: ComponentFixture<ImportCertAndDeliVerifnUpdateComponent>;
    let service: ImportCertAndDeliVerifnService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportCertAndDeliVerifnUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ImportCertAndDeliVerifnUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImportCertAndDeliVerifnUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportCertAndDeliVerifnService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImportCertAndDeliVerifn(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImportCertAndDeliVerifn();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
