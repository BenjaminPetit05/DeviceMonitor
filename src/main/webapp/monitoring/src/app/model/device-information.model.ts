/**
 * The model for the information of each device
 */
export class DeviceInformation {

  constructor(deviceName: string, numberOfMessageSend: number) {
    this.deviceName = deviceName;
    this.numberOfMessageSend = numberOfMessageSend
    ;
  }

  deviceName: string;
  numberOfMessageSend: number;

}
