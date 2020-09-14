import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ManufCostStmtUpdateComponent } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt-update.component';
import { ManufCostStmtService } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt.service';
import { ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufCostStmt Management Update Component', () => {
    let comp: ManufCostStmtUpdateComponent;
    let fixture: ComponentFixture<ManufCostStmtUpdateComponent>;
    let service: ManufCostStmtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ManufCostStmtUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ManufCostStmtUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufCostStmtUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufCostStmtService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ManufCostStmt(123);
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
        const entity = new ManufCostStmt();
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
