import {Component, OnDestroy, OnInit} from "@angular/core";
import {DeviceInformation} from "../model/device-information.model";
import {MeasureService} from "../service/measure.service";

/**
 * Component for managing the display of device information
 */
@Component({
  selector: 'device-display',
  templateUrl: './device-display.component.html',
  styleUrl: './device-display.component.scss',
  providers: [MeasureService]
})
export class DeviceDisplayComponent implements OnInit, OnDestroy {

  /** The list of all DeviceInformation */
  protected devicesInformations: DeviceInformation[] = [];
  protected backDevicesInformations: DeviceInformation[] = [];
  protected frontDevicesInformations: DeviceInformation[] = [];

  private refreshDeviceInformationInterval: any

  constructor(private measureService: MeasureService) {
  }

  ngOnInit(): void {
    this.measureService.getDeviceInformation()
      .subscribe(
        infos => {
          this.devicesInformations = infos;
          this.backDevicesInformations = infos.filter(deviceInfo => deviceInfo.deviceName.startsWith("BCK"));
          this.frontDevicesInformations = infos.filter(deviceInfo => deviceInfo.deviceName.startsWith("FNT"));
        }
      )
    this.refreshDeviceInformationInterval = setInterval(() => {
      this.refresh();
    }, 10000)
  }

  /**
   * Method of refreshing device information
   */
  refresh() {
    this.measureService.getDeviceInformation()
      .subscribe(
        infos => {
          this.devicesInformations = infos;
          this.backDevicesInformations = infos.filter(deviceInfo => deviceInfo.deviceName.startsWith("BCK"));
          this.frontDevicesInformations = infos.filter(deviceInfo => deviceInfo.deviceName.startsWith("FNT"));
        }
      )
  }

  ngOnDestroy(): void {
    clearInterval(this.refreshDeviceInformationInterval);
  }
}
