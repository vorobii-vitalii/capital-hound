package com.site.capitalhound.dao.impl;

import com.site.capitalhound.entity.AirMapElevationApiResponse;
import com.site.capitalhound.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AirMapElevationApiRepositoryTest {

    @MockBean
    private RestOperations restOperationsMock;

    @Autowired
    AirMapElevationApiRepository airMapElevationApiRepository;

    private final Map<String, String> mockHeaderParams =
            Map.of("X-API-Key", "airmap");;

    @Test
    public void test_GivenCoordinates_ThenReturnCorrectAPIQueryString() {
        final double latitude = 1, longitude = 2;

        var expected = "https://api.airmap.com/elevation/v1/ele/?points=1.0,2.0";

        var actual =
                airMapElevationApiRepository.constructQueryWithCoordinate(latitude, longitude);

        assertEquals(expected, actual);
    }

    @Test
    public void test_GivenCoordinates_ThenThrowNotFoundException() {
        final double latitude = 1, longitude = 2;

        when(restOperationsMock
                .getForEntity(
                        "https://api.airmap.com/elevation/v1/ele/?points=1.0,2.0",
                        AirMapElevationApiResponse.class,
                        mockHeaderParams))
                .thenThrow(RestClientException.class);

        assertThrows(ApiException.class, () ->
                airMapElevationApiRepository.getResponseByCoordinate(latitude, longitude)
        );
    }

    @Test
    public void test_GivenCoordinates_ThenThrowExceptionDueToWrongStatus() {
        final double latitude = 1, longitude = 2;

        var body = AirMapElevationApiResponse.builder()
                .data(new Integer[]{})
                .status("failure")
                .build();

        var response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restOperationsMock
                .getForEntity(
                        "https://api.airmap.com/elevation/v1/ele/?points=1.0,2.0",
                        AirMapElevationApiResponse.class,
                        mockHeaderParams))
                .thenReturn(response);

        assertThrows(ApiException.class, () ->
                airMapElevationApiRepository.getResponseByCoordinate(latitude, longitude)
        );
    }

    @Test
    public void test_GivenCoordinates_ThenReturnCorrectResult() {
        final double latitude = 1, longitude = 2;

        var body = AirMapElevationApiResponse.builder()
                .data(new Integer[]{150})
                .status("success")
                .build();

        var response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restOperationsMock
                .getForEntity(
                        "https://api.airmap.com/elevation/v1/ele/?points=1.0,2.0",
                        AirMapElevationApiResponse.class,
                        mockHeaderParams))
                .thenReturn(response);

        var actualBody =
                airMapElevationApiRepository.getResponseByCoordinate(latitude, longitude);

        assertEquals(body, actualBody);
    }

}