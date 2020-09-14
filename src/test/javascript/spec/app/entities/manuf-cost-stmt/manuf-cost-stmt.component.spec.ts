import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ManufCostStmtComponent } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt.component';
import { ManufCostStmtService } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt.service';
import { ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufCostStmt Management Component', () => {
    let comp: ManufCostStmtComponent;
    let fixture: ComponentFixture<ManufCostStmtComponent>;
    let service: ManufCostStmtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ManufCostStmtComponent],
      })
        .overrideTemplate(ManufCostStmtComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufCostStmtComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufCostStmtService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ManufCostStmt(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.manufCostStmts && comp.manufCostStmts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
