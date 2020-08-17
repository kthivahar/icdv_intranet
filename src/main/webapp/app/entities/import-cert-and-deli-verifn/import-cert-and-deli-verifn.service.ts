import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';

type EntityResponseType = HttpResponse<IImportCertAndDeliVerifn>;
type EntityArrayResponseType = HttpResponse<IImportCertAndDeliVerifn[]>;

@Injectable({ providedIn: 'root' })
export class ImportCertAndDeliVerifnService {
  public resourceUrl = SERVER_API_URL + 'api/import-cert-and-deli-verifns';

  constructor(protected http: HttpClient) {}

  create(importCertAndDeliVerifn: IImportCertAndDeliVerifn): Observable<EntityResponseType> {
    return this.http.post<IImportCertAndDeliVerifn>(this.resourceUrl, importCertAndDeliVerifn, { observe: 'response' });
  }

  update(importCertAndDeliVerifn: IImportCertAndDeliVerifn): Observable<EntityResponseType> {
    return this.http.put<IImportCertAndDeliVerifn>(this.resourceUrl, importCertAndDeliVerifn, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IImportCertAndDeliVerifn>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IImportCertAndDeliVerifn[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
