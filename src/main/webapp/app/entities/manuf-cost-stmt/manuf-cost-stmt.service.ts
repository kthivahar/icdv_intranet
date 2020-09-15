import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';

type EntityResponseType = HttpResponse<IManufCostStmt>;
type EntityArrayResponseType = HttpResponse<IManufCostStmt[]>;

@Injectable({ providedIn: 'root' })
export class ManufCostStmtService {
  public resourceUrl = SERVER_API_URL + 'api/manuf-cost-stmts';

  constructor(protected http: HttpClient) {}

  create(manufCostStmt: IManufCostStmt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(manufCostStmt);
    return this.http
      .post<IManufCostStmt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(manufCostStmt: IManufCostStmt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(manufCostStmt);
    return this.http
      .put<IManufCostStmt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IManufCostStmt>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IManufCostStmt[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(manufCostStmt: IManufCostStmt): IManufCostStmt {
    const copy: IManufCostStmt = Object.assign({}, manufCostStmt, {
      statementDate:
        manufCostStmt.statementDate && manufCostStmt.statementDate.isValid() ? manufCostStmt.statementDate.toJSON() : undefined,
      declareOn1: manufCostStmt.declareOn1 && manufCostStmt.declareOn1.isValid() ? manufCostStmt.declareOn1.toJSON() : undefined,
      declareOn2: manufCostStmt.declareOn2 && manufCostStmt.declareOn2.isValid() ? manufCostStmt.declareOn2.toJSON() : undefined,
      declareOn3: manufCostStmt.declareOn3 && manufCostStmt.declareOn3.isValid() ? manufCostStmt.declareOn3.toJSON() : undefined,
      createdOn: manufCostStmt.createdOn && manufCostStmt.createdOn.isValid() ? manufCostStmt.createdOn.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.statementDate = res.body.statementDate ? moment(res.body.statementDate) : undefined;
      res.body.declareOn1 = res.body.declareOn1 ? moment(res.body.declareOn1) : undefined;
      res.body.declareOn2 = res.body.declareOn2 ? moment(res.body.declareOn2) : undefined;
      res.body.declareOn3 = res.body.declareOn3 ? moment(res.body.declareOn3) : undefined;
      res.body.createdOn = res.body.createdOn ? moment(res.body.createdOn) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((manufCostStmt: IManufCostStmt) => {
        manufCostStmt.statementDate = manufCostStmt.statementDate ? moment(manufCostStmt.statementDate) : undefined;
        manufCostStmt.declareOn1 = manufCostStmt.declareOn1 ? moment(manufCostStmt.declareOn1) : undefined;
        manufCostStmt.declareOn2 = manufCostStmt.declareOn2 ? moment(manufCostStmt.declareOn2) : undefined;
        manufCostStmt.declareOn3 = manufCostStmt.declareOn3 ? moment(manufCostStmt.declareOn3) : undefined;
        manufCostStmt.createdOn = manufCostStmt.createdOn ? moment(manufCostStmt.createdOn) : undefined;
      });
    }
    return res;
  }
}
