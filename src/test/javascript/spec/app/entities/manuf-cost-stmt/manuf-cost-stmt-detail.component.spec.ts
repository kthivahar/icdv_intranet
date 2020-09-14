import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ManufCostStmtDetailComponent } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt-detail.component';
import { ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufCostStmt Management Detail Component', () => {
    let comp: ManufCostStmtDetailComponent;
    let fixture: ComponentFixture<ManufCostStmtDetailComponent>;
    const route = ({ data: of({ manufCostStmt: new ManufCostStmt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ManufCostStmtDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ManufCostStmtDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ManufCostStmtDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load manufCostStmt on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.manufCostStmt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
