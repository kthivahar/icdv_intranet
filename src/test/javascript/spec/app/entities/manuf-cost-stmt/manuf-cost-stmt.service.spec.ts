import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ManufCostStmtService } from 'app/entities/manuf-cost-stmt/manuf-cost-stmt.service';
import { IManufCostStmt, ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';
import { Status } from 'app/shared/model/enumerations/status.model';

describe('Service Tests', () => {
  describe('ManufCostStmt Service', () => {
    let injector: TestBed;
    let service: ManufCostStmtService;
    let httpMock: HttpTestingController;
    let elemDefault: IManufCostStmt;
    let expectedResult: IManufCostStmt | IManufCostStmt[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ManufCostStmtService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ManufCostStmt(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        Status.OPEN,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            statementDate: currentDate.format(DATE_TIME_FORMAT),
            declareOn1: currentDate.format(DATE_TIME_FORMAT),
            declareOn2: currentDate.format(DATE_TIME_FORMAT),
            declareOn3: currentDate.format(DATE_TIME_FORMAT),
            createdOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ManufCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            statementDate: currentDate.format(DATE_TIME_FORMAT),
            declareOn1: currentDate.format(DATE_TIME_FORMAT),
            declareOn2: currentDate.format(DATE_TIME_FORMAT),
            declareOn3: currentDate.format(DATE_TIME_FORMAT),
            createdOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statementDate: currentDate,
            declareOn1: currentDate,
            declareOn2: currentDate,
            declareOn3: currentDate,
            createdOn: currentDate,
          },
          returnedFromService
        );

        service.create(new ManufCostStmt()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ManufCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            nameOfManufacturer: 'BBBBBB',
            uniqueEntityNumber: 'BBBBBB',
            ftaScheme: 'BBBBBB',
            isQRVC: true,
            isCTC: true,
            isMP: true,
            descriptionOfGoods: 'BBBBBB',
            model: 'BBBBBB',
            hsCodeOfGoods: 'BBBBBB',
            statementDate: currentDate.format(DATE_TIME_FORMAT),
            incotermOfFta: 'BBBBBB',
            fobValueOfGoods: 1,
            noOfMCSUnits: 1,
            directLabourCost: 1,
            directOverheadCost: 1,
            profit: 1,
            otherCost: 1,
            isDeclared1: true,
            declaringName1: 'BBBBBB',
            declarePosition1: 'BBBBBB',
            declareOn1: currentDate.format(DATE_TIME_FORMAT),
            isDeclared2: true,
            declaringName2: 'BBBBBB',
            declarePosition2: 'BBBBBB',
            declareOn2: currentDate.format(DATE_TIME_FORMAT),
            isDeclared3: true,
            declaringName3: 'BBBBBB',
            declarePosition3: 'BBBBBB',
            declareOn3: currentDate.format(DATE_TIME_FORMAT),
            totalNonOrigMat: 1,
            totalOrigMat: 1,
            qvcRvc: 1,
            status: 'BBBBBB',
            createdOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statementDate: currentDate,
            declareOn1: currentDate,
            declareOn2: currentDate,
            declareOn3: currentDate,
            createdOn: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ManufCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            nameOfManufacturer: 'BBBBBB',
            uniqueEntityNumber: 'BBBBBB',
            ftaScheme: 'BBBBBB',
            isQRVC: true,
            isCTC: true,
            isMP: true,
            descriptionOfGoods: 'BBBBBB',
            model: 'BBBBBB',
            hsCodeOfGoods: 'BBBBBB',
            statementDate: currentDate.format(DATE_TIME_FORMAT),
            incotermOfFta: 'BBBBBB',
            fobValueOfGoods: 1,
            noOfMCSUnits: 1,
            directLabourCost: 1,
            directOverheadCost: 1,
            profit: 1,
            otherCost: 1,
            isDeclared1: true,
            declaringName1: 'BBBBBB',
            declarePosition1: 'BBBBBB',
            declareOn1: currentDate.format(DATE_TIME_FORMAT),
            isDeclared2: true,
            declaringName2: 'BBBBBB',
            declarePosition2: 'BBBBBB',
            declareOn2: currentDate.format(DATE_TIME_FORMAT),
            isDeclared3: true,
            declaringName3: 'BBBBBB',
            declarePosition3: 'BBBBBB',
            declareOn3: currentDate.format(DATE_TIME_FORMAT),
            totalNonOrigMat: 1,
            totalOrigMat: 1,
            qvcRvc: 1,
            status: 'BBBBBB',
            createdOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statementDate: currentDate,
            declareOn1: currentDate,
            declareOn2: currentDate,
            declareOn3: currentDate,
            createdOn: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ManufCostStmt', () => {
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
