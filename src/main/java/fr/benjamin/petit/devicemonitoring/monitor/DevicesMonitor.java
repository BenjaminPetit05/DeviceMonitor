package fr.benjamin.petit.devicemonitoring.monitor;

import fr.benjamin.petit.devicemonitoring.model.Device;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Random;

/**
 * Class that manage devices and simulate the fact that a specific device send a message through network
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
public class DevicesMonitor {

    /**
     * The list of devices "running" and sending message
     */
    private final List<Device> runningDevices = initRunningDevices();

    private final Random random = new Random();

    /**
     * Select a random device in the list of runningDevices
     *
     * @return a {@link Device}
     */
    public Device randomize() {
        return this.runningDevices.get(random.nextInt(this.runningDevices.size()));
    }

    /**
     * Create a specific list of devices
     *
     * @return a List of {@link Device}
     */
    private List<Device> initRunningDevices() {
        Device firstDevice = new Device("BCK-001");
        Device secondDevice = new Device("BCK-002");
        Device thirdDevice = new Device("BCK-003");

        return List.of(firstDevice, secondDevice, thirdDevice);
    }


}
