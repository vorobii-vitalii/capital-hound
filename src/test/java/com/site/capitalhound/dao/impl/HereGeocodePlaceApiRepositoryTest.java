package com.site.capitalhound.dao.impl;

import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.entity.api.Item;
import com.site.capitalhound.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HereGeocodePlaceApiRepositoryTest {

    @MockBean
    private RestOperations restOperationsMock;

    @Autowired
    private HereGeocodePlaceApiRepository hereGeocodePlaceApiRepository;

    @Test
    public void test_GivenAddress_ThenReturnCorrectAPIQuery() {
        var address = "Some address";

        var expected = "https://geocode.search.hereapi.com/v1/geocode?apiKey=here&q=Some address";

        var actual = hereGeocodePlaceApiRepository.constructQueryWithAddress(address);

        assertEquals(expected, actual);
    }

    @Test
    public void test_GivenMeaninglessAddress_ThenGracefullyHandleNotFoundException() {
        var meaningLessAddress = "Meaningless123";

        when(restOperationsMock
                .getForEntity("https://geocode.search.hereapi.com/v1/geocode?apiKey=here&q=Meaningless123",
                        HereGeocodeResponse.class))
                .thenThrow(RestClientException.class);

        assertThrows(ApiException.class, () ->
                hereGeocodePlaceApiRepository.getResponse(meaningLessAddress)
        );
    }

    @Test
    public void test_GivenCorrectAddress_ThenVerifyReturnedObject() {
        var address = "Ivano-Frankivsk";

        var body = new HereGeocodeResponse();

        body.setItems(List.of(new Item(), new Item()));

        var response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restOperationsMock
                .getForEntity("https://geocode.search.hereapi.com/v1/geocode?apiKey=here&q=Ivano-Frankivsk",
                        HereGeocodeResponse.class))
                .thenReturn(response);

        var actualBody = hereGeocodePlaceApiRepository.getResponse(address);

        assertEquals(body, actualBody);
    }

}