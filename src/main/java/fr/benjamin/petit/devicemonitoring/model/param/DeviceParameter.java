package fr.benjamin.petit.devicemonitoring.model.param;

import lombok.*;

import java.time.Instant;


/**
 * Represent the data sent by a device
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceParameter {

    @NonNull
    private String name;
    @NonNull
    private Integer measure;
    @NonNull
    private Instant dateOfMeasure;

}
