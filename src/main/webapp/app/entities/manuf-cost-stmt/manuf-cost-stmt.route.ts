import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IManufCostStmt, ManufCostStmt } from 'app/shared/model/manuf-cost-stmt.model';
import { ManufCostStmtService } from './manuf-cost-stmt.service';
import { ManufCostStmtComponent } from './manuf-cost-stmt.component';
import { ManufCostStmtDetailComponent } from './manuf-cost-stmt-detail.component';
import { ManufCostStmtUpdateComponent } from './manuf-cost-stmt-update.component';

@Injectable({ providedIn: 'root' })
export class ManufCostStmtResolve implements Resolve<IManufCostStmt> {
  constructor(private service: ManufCostStmtService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IManufCostStmt> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((manufCostStmt: HttpResponse<ManufCostStmt>) => {
          if (manufCostStmt.body) {
            return of(manufCostStmt.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ManufCostStmt());
  }
}

export const manufCostStmtRoute: Routes = [
  {
    path: '',
    component: ManufCostStmtComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.manufCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ManufCostStmtDetailComponent,
    resolve: {
      manufCostStmt: ManufCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.manufCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ManufCostStmtUpdateComponent,
    resolve: {
      manufCostStmt: ManufCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.manufCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ManufCostStmtUpdateComponent,
    resolve: {
      manufCostStmt: ManufCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'icdvIntranetApp.manufCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
