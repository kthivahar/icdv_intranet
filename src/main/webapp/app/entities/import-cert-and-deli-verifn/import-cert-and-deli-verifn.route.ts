import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IImportCertAndDeliVerifn, ImportCertAndDeliVerifn } from 'app/shared/model/import-cert-and-deli-verifn.model';
import { ImportCertAndDeliVerifnService } from './import-cert-and-deli-verifn.service';
import { ImportCertAndDeliVerifnComponent } from './import-cert-and-deli-verifn.component';
import { ImportCertAndDeliVerifnDetailComponent } from './import-cert-and-deli-verifn-detail.component';
import { ImportCertAndDeliVerifnUpdateComponent } from './import-cert-and-deli-verifn-update.component';

@Injectable({ providedIn: 'root' })
export class ImportCertAndDeliVerifnResolve implements Resolve<IImportCertAndDeliVerifn> {
  constructor(private service: ImportCertAndDeliVerifnService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IImportCertAndDeliVerifn> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((importCertAndDeliVerifn: HttpResponse<ImportCertAndDeliVerifn>) => {
          if (importCertAndDeliVerifn.body) {
            return of(importCertAndDeliVerifn.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ImportCertAndDeliVerifn());
  }
}

export const importCertAndDeliVerifnRoute: Routes = [
  {
    path: '',
    component: ImportCertAndDeliVerifnComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importCertAndDeliVerifn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ImportCertAndDeliVerifnDetailComponent,
    resolve: {
      importCertAndDeliVerifn: ImportCertAndDeliVerifnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importCertAndDeliVerifn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ImportCertAndDeliVerifnUpdateComponent,
    resolve: {
      importCertAndDeliVerifn: ImportCertAndDeliVerifnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importCertAndDeliVerifn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ImportCertAndDeliVerifnUpdateComponent,
    resolve: {
      importCertAndDeliVerifn: ImportCertAndDeliVerifnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importCertAndDeliVerifn.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
