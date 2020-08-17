import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportInformationUpdateComponent } from 'app/entities/import-information/import-information-update.component';
import { ImportInformationService } from 'app/entities/import-information/import-information.service';
import { ImportInformation } from 'app/shared/model/import-information.model';

describe('Component Tests', () => {
  describe('ImportInformation Management Update Component', () => {
    let comp: ImportInformationUpdateComponent;
    let fixture: ComponentFixture<ImportInformationUpdateComponent>;
    let service: ImportInformationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportInformationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ImportInformationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImportInformationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportInformationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ImportInformation(123);
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
        const entity = new ImportInformation();
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
