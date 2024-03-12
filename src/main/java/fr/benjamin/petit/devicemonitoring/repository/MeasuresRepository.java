package fr.benjamin.petit.devicemonitoring.repository;

import fr.benjamin.petit.devicemonitoring.model.dto.DeviceInformationDTO;
import fr.benjamin.petit.devicemonitoring.model.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for the entity {@link Measure}
 */
public interface MeasuresRepository extends JpaRepository<Measure, Long> {

    /**
     * Method to retrieve basic information for each device, in this case: name and number of the message sent.
     * Returns the list of {@link DeviceInformationDTO} containing the device name and the number of the message sent.
     */
    @Query(value = """
            SELECT deviceName as deviceName, Count(deviceName) as numberOfMessageSend FROM Measure GROUP BY deviceName
            """)
    List<DeviceInformationDTO> findDevicesInformations();

    /**
     * Endpoint to retrieve all the measures of a device stored in database
     *
     * @param deviceName : The name of the device from which you wish to retrieve measures from the database
     * @return the list of measures in the database for the specific device
     */
    @Query("""
            SELECT m FROM Measure as m WHERE m.deviceName = :deviceName
            """)
    List<Measure> findAllMeasureByName(String deviceName);
}
