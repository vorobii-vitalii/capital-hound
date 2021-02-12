package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.ElevationApiRepository;
import com.site.capitalhound.entity.AirMapElevationApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AirMapElevationServiceTest {

    @MockBean
    private ElevationApiRepository<AirMapElevationApiResponse> airMapElevationApiRepositoryMock;

    @Autowired
    private AirMapElevationService airMapElevationService;

    @Test
    public void test_GivenCoordinates_ServiceProduceCorrectResult() {
        double longitude = 1.0;
        double latitude = 2.0;

        var response = AirMapElevationApiResponse.builder()
                .data(new Integer[]{100})
                .status("success")
                .build();

        when(airMapElevationApiRepositoryMock.getResponseByCoordinate(latitude, longitude))
                .thenReturn(response);

        var result = airMapElevationService.getByCoordinates(latitude, longitude);

        assertEquals(100, result);
    }


}