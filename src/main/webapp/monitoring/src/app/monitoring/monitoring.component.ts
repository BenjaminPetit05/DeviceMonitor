import {Component} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MonitoringService} from "../service/monitoring.service";
import {GlobalConstant} from "../common/global-constant";
import {DeviceParameter} from "../model/device-parameter.model";

/**
 * Represent a device
 */
class Device {

  name: string;

  constructor(name: string) {
    this.name = name;
  }


}

/**
 * Data monitoring component that allows you to start and stop monitoring.
 */
@Component({
  selector: 'monitoring-display',
  templateUrl: './monitoring.component.html',
  styleUrl: './monitoring.component.scss',
  providers: [MonitoringService]
})
export class MonitoringComponent {

  constructor(private monitoringService: MonitoringService,
              private snackBar: MatSnackBar) {
  }

  private intervalForSendingData: any;

  /**
   * Monitoring startup method that triggers devices to send data
   */
  startMonitoring() {
    this.monitoringService.startMonitoring().subscribe();

    this.intervalForSendingData = setInterval(() => {
      this.sendData();
    }, 10000)

    this.snackBar.open(GlobalConstant.RUNNING_MESSAGE, GlobalConstant.CLOSE_ACTION)
  }

  /**
   * Monitoring stop method, stop devices from sending data
   */
  stopMonitoring() {
    this.monitoringService.stopMonitoring().subscribe(
      () => this.snackBar.open(GlobalConstant.STOPPED_MESSAGE, GlobalConstant.CLOSE_ACTION)
    );
    clearInterval(this.intervalForSendingData);
  }


  /**
   * Method used vy device to that send data
   */
  private sendData() {
    const runningDevices = this.initRunningDevice();
    const selectedDevice = runningDevices[Math.floor(Math.random() * runningDevices.length)];
    const now = new Date();
    const deviceParameter: DeviceParameter = new DeviceParameter(selectedDevice.name, this.generateValue(now), new Date());
    this.monitoringService.sendMeasure(deviceParameter).subscribe();

  }

  /**
   * Method for generating the arbitrary data to be sent
   *
   * @param now The parameter used to calculate the measure
   * @return the value of the measure which is the sum of all the digits of the timestamp
   */
  private generateValue(now: Date): number {
    const measure = now.getTime().toString();
    return measure.split("")
      .map(s => parseInt(s, 0))
      .reduce((acc, curr) => acc + curr, 0);
  }

  /**
   * Method used to initialize the device list
   */
  private initRunningDevice(): Device[] {
    return [new Device("FNT-001"), new Device("FNT-002"), new Device("FNT-003")]
  }

}
