package fr.benjamin.petit.devicemonitoring.model.dto;

import lombok.*;

import java.time.Instant;

/**
 * The DTO and the {@link fr.benjamin.petit.devicemonitoring.model.entity.Measure} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDTO {

    private Long id;
    @NonNull
    private Integer measureValue;
    @NonNull
    private String deviceName;
    @NonNull
    /** The data send by the device, correspond to the sum of the digits of the hashcode of the current timestamp */
    private Instant dateOfMeasure;

}
