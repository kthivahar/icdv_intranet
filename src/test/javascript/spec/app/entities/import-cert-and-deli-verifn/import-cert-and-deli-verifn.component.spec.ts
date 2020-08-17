import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportCertAndDeliVerifnComponent } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.component';
import { ImportCertAndDeliVerifnService } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service';
import { ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';

describe('Component Tests', () => {
  describe('ImportCertAndDeliVerifn Management Component', () => {
    let comp: ImportCertAndDeliVerifnComponent;
    let fixture: ComponentFixture<ImportCertAndDeliVerifnComponent>;
    let service: ImportCertAndDeliVerifnService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportCertAndDeliVerifnComponent],
      })
        .overrideTemplate(ImportCertAndDeliVerifnComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImportCertAndDeliVerifnComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportCertAndDeliVerifnService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ImportCertAndDeliVerifn(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.importCertAndDeliVerifns && comp.importCertAndDeliVerifns[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
