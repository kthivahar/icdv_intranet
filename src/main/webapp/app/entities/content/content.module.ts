import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IcdvIntranetSharedModule } from 'app/shared/shared.module';
import { ContentComponent } from './content.component';
import { ContentDetailComponent } from './content-detail.component';
import { ContentUpdateComponent } from './content-update.component';
import { ContentDeleteDialogComponent } from './content-delete-dialog.component';
import { contentRoute } from './content.route';

@NgModule({
  imports: [IcdvIntranetSharedModule, RouterModule.forChild(contentRoute)],
  declarations: [ContentComponent, ContentDetailComponent, ContentUpdateComponent, ContentDeleteDialogComponent],
  entryComponents: [ContentDeleteDialogComponent],
})
export class IcdvIntranetContentModule {}
