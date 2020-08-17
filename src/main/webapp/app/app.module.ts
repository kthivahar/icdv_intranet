import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { IcdvIntranetSharedModule } from 'app/shared/shared.module';
import { IcdvIntranetCoreModule } from 'app/core/core.module';
import { IcdvIntranetAppRoutingModule } from './app-routing.module';
import { IcdvIntranetHomeModule } from './home/home.module';
import { IcdvIntranetEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    IcdvIntranetSharedModule,
    IcdvIntranetCoreModule,
    IcdvIntranetHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    IcdvIntranetEntityModule,
    IcdvIntranetAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class IcdvIntranetAppModule {}
