import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClientService} from "./http-client.service";
import {DeviceParameter} from "../model/device-parameter.model";

/**
 * The service that manages the monitoring
 */
@Injectable()
export class MonitoringService extends HttpClientService {

  /**
   * Monitoring startup method that triggers devices to send data
   */
  startMonitoring(): Observable<void> {
    return this.http.get<void>(this.monitoringUrl + '/start');
  }

  /**
   * Monitoring stop method, stop devices from sending data
   */
  stopMonitoring(): Observable<void> {
    return this.http.get<void>(this.monitoringUrl + '/stop');
  }

  /**
   * Endpoint to retrieve all the measures of a device stored in database
   * @param deviceParameter The information sent by the device (device name, measure, measure date)
   */
  sendMeasure(deviceParameter: DeviceParameter) {
    return this.http.post<any>(this.measureUrl + "/send", deviceParameter);
  }
}
