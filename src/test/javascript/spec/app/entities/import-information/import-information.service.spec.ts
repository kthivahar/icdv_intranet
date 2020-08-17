import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImportInformationService } from 'app/entities/import-information/import-information.service';
import { IImportInformation, ImportInformation } from 'app/shared/model/import-information.model';

describe('Service Tests', () => {
  describe('ImportInformation Service', () => {
    let injector: TestBed;
    let service: ImportInformationService;
    let httpMock: HttpTestingController;
    let elemDefault: IImportInformation;
    let expectedResult: IImportInformation | IImportInformation[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ImportInformationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ImportInformation(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ImportInformation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ImportInformation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ImportInformation', () => {
        const returnedFromService = Object.assign(
          {
            externalId: 'BBBBBB',
            descriptionOfGoods: 'BBBBBB',
            hsCode: 'BBBBBB',
            unit: 'BBBBBB',
            quantity: 1,
            value: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ImportInformation', () => {
        const returnedFromService = Object.assign(
          {
            externalId: 'BBBBBB',
            descriptionOfGoods: 'BBBBBB',
            hsCode: 'BBBBBB',
            unit: 'BBBBBB',
            quantity: 1,
            value: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ImportInformation', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
