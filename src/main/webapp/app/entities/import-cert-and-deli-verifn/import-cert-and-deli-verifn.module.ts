import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IcdvIntranetSharedModule } from 'app/shared/shared.module';
import { ImportCertAndDeliVerifnComponent } from './import-cert-and-deli-verifn.component';
import { ImportCertAndDeliVerifnDetailComponent } from './import-cert-and-deli-verifn-detail.component';
import { ImportCertAndDeliVerifnUpdateComponent } from './import-cert-and-deli-verifn-update.component';
import { ImportCertAndDeliVerifnDeleteDialogComponent } from './import-cert-and-deli-verifn-delete-dialog.component';
import { importCertAndDeliVerifnRoute } from './import-cert-and-deli-verifn.route';
import {FormioModule} from "angular-formio";

@NgModule({
    imports: [IcdvIntranetSharedModule, RouterModule.forChild(importCertAndDeliVerifnRoute), FormioModule],
  declarations: [
    ImportCertAndDeliVerifnComponent,
    ImportCertAndDeliVerifnDetailComponent,
    ImportCertAndDeliVerifnUpdateComponent,
    ImportCertAndDeliVerifnDeleteDialogComponent,
  ],
  entryComponents: [ImportCertAndDeliVerifnDeleteDialogComponent],
})
export class IcdvIntranetImportCertAndDeliVerifnModule {}
