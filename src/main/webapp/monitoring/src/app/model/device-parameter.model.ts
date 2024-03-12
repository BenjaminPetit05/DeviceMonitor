/**
 * Represent the data sent by a device
 */
export class DeviceParameter {

  name: string;
  measure: number;
  dateOfMeasure: Date;

  constructor(name: string, measure: number, dateOfMeasure: Date) {
    this.name = name;
    this.measure = measure;
    this.dateOfMeasure = dateOfMeasure;
  }
}
