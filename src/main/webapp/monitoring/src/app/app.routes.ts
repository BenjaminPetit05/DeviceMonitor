import {Routes} from "@angular/router";
import {DeviceDisplayDetailsComponent} from "./device-display-details/device-display-details.component";
import {DeviceDisplayComponent} from "./device-display/device-display.component";

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: DeviceDisplayComponent},
  {path: 'device-details/:name', component: DeviceDisplayDetailsComponent},
];
