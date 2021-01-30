package com.site.capitalhound.dao.impl;

import com.site.capitalhound.dao.CountryInfoApiRepository;
import com.site.capitalhound.entity.CountryInfo;
import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@Repository
@RequiredArgsConstructor
public class RestCountriesCountryInfoApiRepository implements CountryInfoApiRepository<CountryInfo> {
    private static final String QUERY_URL_PREFIX = "https://restcountries.eu/rest/v2/alpha/";

    private final RestOperations restOperations;

    @Override
    public CountryInfo getByCountryCode(String countryCode) {
        try {
            final ResponseEntity<CountryInfo> response =
                    restOperations.getForEntity(QUERY_URL_PREFIX + countryCode, CountryInfo.class);

            if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
                throw new ApiException();
            }

            return response.getBody();
        }
        catch (RestClientException e) {
            throw new ApiException();
        }
    }

}
