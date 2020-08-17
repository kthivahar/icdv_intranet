import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IcdvIntranetTestModule } from '../../../test.module';
import { ImportInformationDetailComponent } from 'app/entities/import-information/import-information-detail.component';
import { ImportInformation } from 'app/shared/model/import-information.model';

describe('Component Tests', () => {
  describe('ImportInformation Management Detail Component', () => {
    let comp: ImportInformationDetailComponent;
    let fixture: ComponentFixture<ImportInformationDetailComponent>;
    const route = ({ data: of({ importInformation: new ImportInformation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IcdvIntranetTestModule],
        declarations: [ImportInformationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ImportInformationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ImportInformationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load importInformation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.importInformation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
