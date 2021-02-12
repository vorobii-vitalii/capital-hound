package com.site.capitalhound.dao.impl;

import com.site.capitalhound.entity.CountryInfo;
import com.site.capitalhound.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestCountriesCountryInfoApiRepositoryTest {

    @MockBean
    private RestOperations restOperationsMock;

    @Autowired
    private RestCountriesCountryInfoApiRepository restCountriesCountryInfoApiRepository;

    @Test
    public void test_WhenGivenCountryWithMeaninglessName_ThrowApiException() {
        var meaningLessCountryName = "asdasdasd";

        when(restOperationsMock
                .getForEntity("https://restcountries.eu/rest/v2/alpha/" + meaningLessCountryName,
                        CountryInfo.class))
                .thenThrow(RestClientException.class);

        assertThrows(ApiException.class, () ->
                restCountriesCountryInfoApiRepository.getByCountryCode(meaningLessCountryName));
    }

    @Test
    public void test_WhenGivenCountryName_ProduceCorrectResult() {
        var countryCode = "EN";

        var body = CountryInfo.builder()
                .area(24.0)
                .name(countryCode)
                .capital("London")
                .population(1_500_000)
                .region("Europe")
                .build();

        var response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restOperationsMock
                .getForEntity("https://restcountries.eu/rest/v2/alpha/" + countryCode,
                        CountryInfo.class))
                .thenReturn(response);

        var actualBody = restCountriesCountryInfoApiRepository.getByCountryCode(countryCode);

        assertEquals(body, actualBody);
    }


}