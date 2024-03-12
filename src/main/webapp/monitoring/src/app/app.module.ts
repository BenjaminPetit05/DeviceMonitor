import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {DeviceDisplayComponent} from "./device-display/device-display.component";
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {provideAnimations} from "@angular/platform-browser/animations";
import {MonitoringComponent} from "./monitoring/monitoring.component";
import {RouterLink, RouterModule} from "@angular/router";
import {routes} from "./app.routes";
import {DeviceDisplayDetailsComponent} from "./device-display-details/device-display-details.component";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {MatSort, MatSortHeader} from "@angular/material/sort";

@NgModule({
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    RouterLink,
    RouterModule.forRoot(routes),
    MatTable,
    MatSort,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatHeaderCellDef,
    MatHeaderRow,
    MatRow,
    MatCellDef,
    MatHeaderRowDef,
    MatRowDef,
    MatSortHeader
  ],
  declarations: [
    AppComponent,
    DeviceDisplayComponent,
    MonitoringComponent,
    DeviceDisplayDetailsComponent
  ],
  providers: [provideAnimations()],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppModule {
}
