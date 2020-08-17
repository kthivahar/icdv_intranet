import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IcdvIntranetSharedModule } from 'app/shared/shared.module';
import { ImportInformationComponent } from './import-information.component';
import { ImportInformationDetailComponent } from './import-information-detail.component';
import { ImportInformationUpdateComponent } from './import-information-update.component';
import { ImportInformationDeleteDialogComponent } from './import-information-delete-dialog.component';
import { importInformationRoute } from './import-information.route';

@NgModule({
  imports: [IcdvIntranetSharedModule, RouterModule.forChild(importInformationRoute)],
  declarations: [
    ImportInformationComponent,
    ImportInformationDetailComponent,
    ImportInformationUpdateComponent,
    ImportInformationDeleteDialogComponent,
  ],
  entryComponents: [ImportInformationDeleteDialogComponent],
})
export class IcdvIntranetImportInformationModule {}
