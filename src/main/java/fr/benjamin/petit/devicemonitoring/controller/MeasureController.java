package fr.benjamin.petit.devicemonitoring.controller;

import fr.benjamin.petit.devicemonitoring.model.dto.DeviceInformationDTO;
import fr.benjamin.petit.devicemonitoring.model.dto.MeasureDTO;
import fr.benjamin.petit.devicemonitoring.model.param.DeviceParameter;
import fr.benjamin.petit.devicemonitoring.service.MeasureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to manage measure (creation, sending, retrieving ...)
 */
@RestController
@RequestMapping("api/measures")
@RequiredArgsConstructor
@Slf4j
public class MeasureController {

    private final MeasureService measureService;

    /**
     * Endpoint to send/receive measurements from devices and store them in the database.
     *
     * @param deviceParameter : The information sent by the device (device name, measure, measure date)
     * @return The measure {@link MeasureDTO} created based on the information provided
     */
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public MeasureDTO send(@RequestBody DeviceParameter deviceParameter) {
        log.debug("Data sent by device : {}", deviceParameter);
        return measureService.send(deviceParameter);
    }

    /**
     * Endpoint to retrieve all the measures stored in database
     *
     * @return the list of measures in the database
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeasureDTO> getAllMeasures() {
        log.debug("Get all measures stored in database");
        return measureService.getAll();
    }

    /**
     * Endpoint to retrieve all the measures of a device stored in database
     *
     * @param deviceName : The name of the device from which you wish to retrieve measures from the database
     * @return the list of measures in the database for the specific device
     */
    @GetMapping("/{deviceName}")
    @ResponseStatus(HttpStatus.OK)
    public List<MeasureDTO> getAllMeasuresForSpecificDevice(@PathVariable String deviceName) {
        log.debug("Get all measures stored in database for the selected device : {}", deviceName);
        return measureService.getAllMeasuresForSpecificDevice(deviceName);
    }

    /**
     * Endpoint to retrieve specific information for each device (name and number of message)
     *
     * @return a {@link DeviceInformationDTO} containing the name and number of the message sent by the device.
     */
    @GetMapping("/runningDeviceInformation")
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceInformationDTO> getDevicesInformations() {
        log.debug("Retrieve all devices names and number of message send by device");
        return measureService.getDevicesInformations();
    }

}
