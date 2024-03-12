import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

/**
 * The service that manage the Httpclient
 */
@Injectable()
export class HttpClientService {
  /** The base url for measures services */
  protected readonly measureUrl = 'api/measures';
  /** The base url for monitoring services */
  protected readonly monitoringUrl = 'api/monitoring';

  constructor(
    protected http: HttpClient
  ) {
  }
}
