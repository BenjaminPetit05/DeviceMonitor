import {HttpClientService} from "./http-client.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {DeviceInformation} from "../model/device-information.model";
import {Measure} from "../model/measure.model";


@Injectable()
export class MeasureService extends HttpClientService {

  /**
   * Method for retrieving device information
   */
  getDeviceInformation(): Observable<DeviceInformation[]> {
    return this.http.get<DeviceInformation[]>(this.measureUrl + '/runningDeviceInformation');
  }

  /**
   * Method to retrieve all the measures for a specific device
   * @param deviceName The name of the device
   */
  getAllByDeviceName(deviceName: string): Observable<Measure[]> {
    return this.http.get<Measure[]>(this.measureUrl + `/${deviceName}`);
  }

}
