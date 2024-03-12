/**
 * Represent the measure sent by a device and stored in database
 */
export class Measure {
  id: number;
  deviceName: string;
  /** The data send by the device, correspond to the sum of the digits of the hashcode of the current timestamp */
  measureValue: number;
  dateOfMeasure: Date;


  constructor(id: number, deviceName: string, measureValue: number, dateOfMeasure: Date) {
    this.id = id;
    this.deviceName = deviceName;
    this.measureValue = measureValue;
    this.dateOfMeasure = dateOfMeasure;
  }
}
