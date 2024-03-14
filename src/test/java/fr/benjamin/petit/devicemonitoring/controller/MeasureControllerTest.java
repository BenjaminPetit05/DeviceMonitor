package fr.benjamin.petit.devicemonitoring.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.benjamin.petit.devicemonitoring.model.entity.Measure;
import fr.benjamin.petit.devicemonitoring.model.param.DeviceParameter;
import fr.benjamin.petit.devicemonitoring.repository.MeasuresRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MeasureControllerTest {

    private final MockMvc mockMvc;

    private final MeasuresRepository measuresRepository;

    private final Instant now = Instant.now();

    private Measure measure;

    @Autowired
    MeasureControllerTest(MockMvc mockMvc, MeasuresRepository measuresRepository) {
        this.mockMvc = mockMvc;
        this.measuresRepository = measuresRepository;
    }

    @BeforeEach
    void setUp() {
        measure = Measure.builder()
                .deviceName("TEST-001")
                .measureValue(42)
                .dateOfMeasure(now)
                .build();
    }

    @AfterEach
    void cleanUp() {
        measuresRepository.deleteAll();
    }

    @Test
    void shouldGetAllTheMeasureStoredInDatabase() throws Exception {
        measuresRepository.save(measure);
        Assertions.assertThat(measuresRepository.findAll()).isNotEmpty();

        mockMvc.perform(get("/api/measures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].measureValue").value(measure.getMeasureValue()))
                .andExpect(jsonPath("$[0].deviceName").value(measure.getDeviceName()))
        // Problem with the H2 database format, so we verify only the presence
                .andExpect(jsonPath("$[0].dateOfMeasure").isNotEmpty());
    }

    @Test
    void shouldSendDataToStore() throws Exception {

        Assertions.assertThat(measuresRepository.findAll()).isEmpty();

        DeviceParameter deviceParameterToSend = DeviceParameter.builder()
                .dateOfMeasure(now.truncatedTo(ChronoUnit.MILLIS))
                .measure(666)
                .name("TEST-OO2")
                .build();


        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule()) //Needed because native ObjectMapper can't bind Instant
                .build();
        String bodyToSend = objectMapper.writeValueAsString(deviceParameterToSend);

        mockMvc.perform(
                post("/api/measures/send")
                        .content(bodyToSend)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.measureValue").value(deviceParameterToSend.getMeasure()))
                .andExpect(jsonPath("$.deviceName").value(deviceParameterToSend.getName()))
                .andExpect(jsonPath("$.dateOfMeasure").isNotEmpty());

        Assertions.assertThat(measuresRepository.findAll()).hasSize(1);
        Measure resultMeasure = measuresRepository.findAll().get(0);
        Assertions.assertThat(resultMeasure.getId()).isNotNull();
        Assertions.assertThat(resultMeasure.getMeasureValue()).isEqualTo(deviceParameterToSend.getMeasure());
        Assertions.assertThat(resultMeasure.getDeviceName()).isEqualTo(deviceParameterToSend.getName());
        Assertions.assertThat(resultMeasure.getDateOfMeasure()).isEqualTo(deviceParameterToSend.getDateOfMeasure());


    }

    @Test
    void shouldGetOnlyTheMeasureForASpecificDevice() throws Exception {
        measuresRepository.save(measure);

        Measure secondMeasure = Measure.builder()
                .deviceName("TEST-003")
                .dateOfMeasure(now)
                .measureValue(42)
                .build();
        measuresRepository.save(secondMeasure);
        Measure thirdMeasure = Measure.builder()
                .deviceName("TEST-003")
                .dateOfMeasure(now)
                .measureValue(84)
                .build();
        measuresRepository.save(thirdMeasure);

        Assertions.assertThat(measuresRepository.findAll()).hasSize(3);

        mockMvc.perform(get("/api/measures/" + secondMeasure.getDeviceName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(2));

    }

    @Test
    void shouldGetRunningInformationForDevice() throws Exception {
        Measure secondMeasure = Measure.builder()
                .deviceName("TEST-003")
                .dateOfMeasure(now)
                .measureValue(42)
                .build();
        measuresRepository.save(secondMeasure);
        Measure thirdMeasure = Measure.builder()
                .deviceName("TEST-003")
                .dateOfMeasure(now)
                .measureValue(84)
                .build();
        measuresRepository.save(thirdMeasure);

        Assertions.assertThat(measuresRepository.findAll()).hasSize(2);

        mockMvc.perform(get("/api/measures/runningDeviceInformation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].deviceName").value(secondMeasure.getDeviceName()))
                .andExpect(jsonPath("$[0].numberOfMessageSend").value(2));

    }

}