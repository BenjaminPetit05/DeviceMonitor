package fr.benjamin.petit.devicemonitoring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

/**
 * The entity to manage information send by the device
 */
@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;

    private Instant dateOfMeasure;

    /**
     * The data send by the device, correspond to the sum of the digits of the hashcode of the current timestamp
     */
    private Integer measureValue;

}
