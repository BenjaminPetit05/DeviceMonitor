package fr.benjamin.petit.devicemonitoring.model.mapper;

import fr.benjamin.petit.devicemonitoring.model.dto.MeasureDTO;
import fr.benjamin.petit.devicemonitoring.model.entity.Measure;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class to convert {@link Measure} entity to {@link MeasureDTO} and vice versa
 */
public class MeasureMapper {

    /**
     * Convert an entity to its DTO
     *
     * @param measure The entity to convert
     * @return the DTO {@link MeasureDTO}
     */
    public MeasureDTO convertEntityToDTO(Measure measure) {

        if (measure == null) {
            return new MeasureDTO();
        }

        return MeasureDTO.builder()
                .id(measure.getId())
                .measureValue(measure.getMeasureValue())
                .deviceName(measure.getDeviceName())
                .dateOfMeasure(measure.getDateOfMeasure())
                .build();
    }

    /**
     * Convert the DTO to its entity
     *
     * @param measureDTO The DTO to convert
     * @return the entity {@link Measure}
     */
    public Measure convertDTOToEntity(MeasureDTO measureDTO) {

        return Measure.builder()
                .id(measureDTO.getId())
                .measureValue(measureDTO.getMeasureValue())
                .deviceName(measureDTO.getDeviceName())
                .dateOfMeasure(measureDTO.getDateOfMeasure())
                .build();
    }

    /**
     * Convert a list of entity {@link Measure} to a list of its DTO {@link MeasureDTO}
     *
     * @param measureListToConvert The list of entity to convert
     * @return the list of DTO
     */
    public List<MeasureDTO> convertEntityListToDtoList(List<Measure> measureListToConvert) {

        if (measureListToConvert == null) {
            return new ArrayList<>();
        }

        return measureListToConvert.stream()
                .map(this::convertEntityToDTO)
                .toList();
    }

}
