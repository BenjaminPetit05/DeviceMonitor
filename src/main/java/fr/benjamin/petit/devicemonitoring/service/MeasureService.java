package fr.benjamin.petit.devicemonitoring.service;

import fr.benjamin.petit.devicemonitoring.model.dto.DeviceInformationDTO;
import fr.benjamin.petit.devicemonitoring.model.dto.MeasureDTO;
import fr.benjamin.petit.devicemonitoring.model.entity.Measure;
import fr.benjamin.petit.devicemonitoring.model.mapper.MeasureMapper;
import fr.benjamin.petit.devicemonitoring.model.param.DeviceParameter;
import fr.benjamin.petit.devicemonitoring.repository.MeasuresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service for the {@link Measure} enity
 */
@Service
@Slf4j
public class MeasureService {

    private final MeasuresRepository measuresRepository;

    private final MeasureMapper measuresMapper = new MeasureMapper();

    public MeasureService(final MeasuresRepository measuresRepository) {
        this.measuresRepository = measuresRepository;
    }

    /**
     * Method for retrieving all the measures stored in the database, converted to DTO.
     *
     * @return the list of {@link MeasureDTO}
     */
    public List<MeasureDTO> getAll() {
        List<Measure> allMeasures = measuresRepository.findAll();
        return measuresMapper.convertEntityListToDtoList(allMeasures);
    }

    /**
     * Method to send new data and store it in the database.
     * param deviceParameter : The parameter sent by the device
     * Returns a {@link MeasureDTO} of the saved measure.
     */
    public MeasureDTO send(DeviceParameter deviceParameter) {
        MeasureDTO measureDTO = MeasureDTO.builder()
                .deviceName(deviceParameter.getName())
                .measureValue(deviceParameter.getMeasure())
                .dateOfMeasure(deviceParameter.getDateOfMeasure())
                .build();
        return this.createMeasure(measureDTO);
    }

    /**
     * Method to save and store a new MeasureDTO in the database.
     * param measureDTO : The {@link MeasureDTO} to store
     * Returns a {@link MeasureDTO} of the saved measure.
     */
    public MeasureDTO createMeasure(MeasureDTO measureDTO) {
        Measure measureStored = measuresRepository.save(measuresMapper.convertDTOToEntity(measureDTO));
        log.debug("Measure stored in database : {}", measureStored);
        return measuresMapper.convertEntityToDTO(measureStored);
    }

    /**
     * Method to retrieve basic information for each device, in this case: name and number of the message sent.
     * Returns the list of {@link DeviceInformationDTO} containing the device name and the number of the message sent.
     */
    public List<DeviceInformationDTO> getDevicesInformations() {
        return this.measuresRepository.findDevicesInformations();
    }

    /**
     * Endpoint to retrieve all the measures of a device stored in database
     *
     * @param deviceName : The name of the device from which you wish to retrieve measures from the database
     * @return the list of measures in the database for the specific device
     */
    public List<MeasureDTO> getAllMeasuresForSpecificDevice(String deviceName) {
        return measuresMapper.convertEntityListToDtoList(this.measuresRepository.findAllMeasureByName(deviceName));
    }
}
