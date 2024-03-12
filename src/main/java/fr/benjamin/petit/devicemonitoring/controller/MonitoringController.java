package fr.benjamin.petit.devicemonitoring.controller;

import fr.benjamin.petit.devicemonitoring.model.Device;
import fr.benjamin.petit.devicemonitoring.monitor.DevicesMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to manage the monitoring session :
 * - start => make the devices send data
 * - stop => make the devices stp sending data
 */
@RestController
@RequestMapping("api/monitoring")
@Slf4j
public class MonitoringController {


    /**
     * The status of the process
     */
    private Status status = Status.STOP;

    @Value("${monitoring.hostname}")
    private String hostName;

    /**
     * Endpoint to start the devices and make them send data
     */
    @GetMapping("/start")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void startMonitoring() {
        status = Status.RUN;
        String baseUrl = "api/measures";
        DevicesMonitor devicesMonitor = new DevicesMonitor();
        log.debug("Start monitoring devices {}", devicesMonitor.getRunningDevices());
        long expectedTime = System.currentTimeMillis();


        // This is the simulation of the devices sending data. The system is manually delayed to avoid spamming data
        while (status.equals(Status.RUN)) {

            if (System.currentTimeMillis() < expectedTime) {
                continue;
            }

            expectedTime += 5000L;
            Device sendingDevice = devicesMonitor.randomize();
            log.debug("Device which send data : {}", sendingDevice);
            sendingDevice.sendData(hostName + baseUrl);

        }


    }

    /**
     * Endpoint to stop the device
     */
    @GetMapping("/stop")
    @ResponseStatus(HttpStatus.OK)
    public void stopMonitoring() {
        log.debug("Stop monitoring");
        status = Status.STOP;
    }

    /**
     * Enumeration for the status of the data sending
     */
    private enum Status {
        RUN,
        STOP
    }
}
