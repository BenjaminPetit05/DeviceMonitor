import {Component, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {MeasureService} from "../service/measure.service";
import {Subscription} from "rxjs";
import {MatTableDataSource} from "@angular/material/table";
import {Measure} from "../model/measure.model";

/**
 * The component that manage the details for each device
 */
@Component({
  selector: 'device-display-details',
  templateUrl: 'device-display-details-component.html',
  styleUrl: 'device-display-details.component.scss',
  providers: [MeasureService]
})
export class DeviceDisplayDetailsComponent implements OnInit, OnDestroy {

  /** The column list to display in the table */
  displayedColumns = ['deviceName', 'measureValue', 'dateOfMeasure'];
  /** The device name to display */
  protected deviceName: string = "";
  /** The subscription on the response of the request to retrieve all the mesures for a specific device */
  private paramsSubscription$: Subscription = new Subscription;
  /** All the mesures to display in the table */
  protected dataSource: MatTableDataSource<Measure> = new MatTableDataSource<Measure>();

  constructor(
    private route: ActivatedRoute,
    private measureService: MeasureService
  ) {
  }

  ngOnInit(): void {

    this.paramsSubscription$ = this.route.paramMap.subscribe(
      params => this.deviceName = params.get("name") === null ? '' : '' + params.get("name")
    )

    this.measureService.getAllByDeviceName(this.deviceName).subscribe(
      devices => {
        this.dataSource = new MatTableDataSource<Measure>(devices);
      }
    )

  }

  ngOnDestroy(): void {
    if (this.paramsSubscription$) this.paramsSubscription$.unsubscribe();
  }
}
