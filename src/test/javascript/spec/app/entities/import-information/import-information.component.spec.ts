import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportInformationComponent } from 'app/entities/import-information/import-information.component';
import { ImportInformationService } from 'app/entities/import-information/import-information.service';
import { ImportInformation } from 'app/shared/model/import-information.model';

describe('Component Tests', () => {
  describe('ImportInformation Management Component', () => {
    let comp: ImportInformationComponent;
    let fixture: ComponentFixture<ImportInformationComponent>;
    let service: ImportInformationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportInformationComponent],
      })
        .overrideTemplate(ImportInformationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ImportInformationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ImportInformationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ImportInformation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.importInformations && comp.importInformations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
