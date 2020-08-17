import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IImportInformation, ImportInformation } from 'app/shared/model/import-information.model';
import { ImportInformationService } from './import-information.service';
import { ImportInformationComponent } from './import-information.component';
import { ImportInformationDetailComponent } from './import-information-detail.component';
import { ImportInformationUpdateComponent } from './import-information-update.component';

@Injectable({ providedIn: 'root' })
export class ImportInformationResolve implements Resolve<IImportInformation> {
  constructor(private service: ImportInformationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IImportInformation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((importInformation: HttpResponse<ImportInformation>) => {
          if (importInformation.body) {
            return of(importInformation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ImportInformation());
  }
}

export const importInformationRoute: Routes = [
  {
    path: '',
    component: ImportInformationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ImportInformationDetailComponent,
    resolve: {
      importInformation: ImportInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ImportInformationUpdateComponent,
    resolve: {
      importInformation: ImportInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ImportInformationUpdateComponent,
    resolve: {
      importInformation: ImportInformationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.importInformation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
