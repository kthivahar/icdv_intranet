import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImportCertAndDeliVerifnService } from 'app/entities/import-cert-and-deli-verifn/import-cert-and-deli-verifn.service';
import { IImportCertAndDeliVerifn, ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { Status } from 'app/shared/model/enumerations/status.model';

describe('Service Tests', () => {
  describe('ImportCertAndDeliVerifn Service', () => {
    let injector: TestBed;
    let service: ImportCertAndDeliVerifnService;
    let httpMock: HttpTestingController;
    let elemDefault: IImportCertAndDeliVerifn;
    let expectedResult: IImportCertAndDeliVerifn | IImportCertAndDeliVerifn[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ImportCertAndDeliVerifnService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ImportCertAndDeliVerifn(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Status.OPEN
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ImportCertAndDeliVerifn', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ImportCertAndDeliVerifn()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ImportCertAndDeliVerifn', () => {
        const returnedFromService = Object.assign(
          {
            externalId: 'BBBBBB',
            companyName: 'BBBBBB',
            uniqueEntityNumberUen: 'BBBBBB',
            address: 'BBBBBB',
            nameOfContactPerson: 'BBBBBB',
            designation: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            exporterCompanyName: 'BBBBBB',
            exporterAddress: 'BBBBBB',
            euCompanyName: 'BBBBBB',
            country: 'BBBBBB',
            radio1: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ImportCertAndDeliVerifn', () => {
        const returnedFromService = Object.assign(
          {
            externalId: 'BBBBBB',
            companyName: 'BBBBBB',
            uniqueEntityNumberUen: 'BBBBBB',
            address: 'BBBBBB',
            nameOfContactPerson: 'BBBBBB',
            designation: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            exporterCompanyName: 'BBBBBB',
            exporterAddress: 'BBBBBB',
            euCompanyName: 'BBBBBB',
            country: 'BBBBBB',
            radio1: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should delete a ImportCertAndDeliVerifn', () => {
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
