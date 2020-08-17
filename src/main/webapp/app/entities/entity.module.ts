import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'import-cert-and-deli-verifn',
        loadChildren: () =>
          import('./import-cert-and-deli-verifn/import-cert-and-deli-verifn.module').then(m => m.IcdvIntranetImportCertAndDeliVerifnModule),
      },
      {
        path: 'import-information',
        loadChildren: () => import('./import-information/import-information.module').then(m => m.IcdvIntranetImportInformationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class IcdvIntranetEntityModule {}
