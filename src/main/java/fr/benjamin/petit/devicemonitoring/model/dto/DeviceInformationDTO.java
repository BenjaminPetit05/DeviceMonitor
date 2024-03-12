package fr.benjamin.petit.devicemonitoring.model.dto;

/***
 * The device information to be displayed (name and number of messages sent). Allow to associate the result
 * with the database query result.
 */
public interface DeviceInformationDTO {

    String getDeviceName();

    Integer getNumberOfMessageSend();

}
