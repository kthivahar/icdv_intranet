import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportCertAndDeliVerifnDetailComponent } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn-detail.component';
import { ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';

describe('Component Tests', () => {
  describe('ImportCertAndDeliVerifn Management Detail Component', () => {
    let comp: ImportCertAndDeliVerifnDetailComponent;
    let fixture: ComponentFixture<ImportCertAndDeliVerifnDetailComponent>;
    const route = ({ data: of({ importCertAndDeliVerifn: new ImportCertAndDeliVerifn(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportCertAndDeliVerifnDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ImportCertAndDeliVerifnDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImportCertAndDeliVerifnDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load importCertAndDeliVerifn on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.importCertAndDeliVerifn).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
