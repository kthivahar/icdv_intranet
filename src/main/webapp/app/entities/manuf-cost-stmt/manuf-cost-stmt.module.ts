import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IcdvIntranetSharedModule } from 'app/shared/shared.module';
import { ManufCostStmtComponent } from './manuf-cost-stmt.component';
import { ManufCostStmtDetailComponent } from './manuf-cost-stmt-detail.component';
import { ManufCostStmtUpdateComponent } from './manuf-cost-stmt-update.component';
import { ManufCostStmtDeleteDialogComponent } from './manuf-cost-stmt-delete-dialog.component';
import { manufCostStmtRoute } from './manuf-cost-stmt.route';

@NgModule({
  imports: [IcdvIntranetSharedModule, RouterModule.forChild(manufCostStmtRoute)],
  declarations: [ManufCostStmtComponent, ManufCostStmtDetailComponent, ManufCostStmtUpdateComponent, ManufCostStmtDeleteDialogComponent],
  entryComponents: [ManufCostStmtDeleteDialogComponent],
})
export class IcdvIntranetManufCostStmtModule {}
