package com.site.capitalhound.dao.impl;

import com.site.capitalhound.dao.PlaceApiRepository;
import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@RequiredArgsConstructor
@Repository
public class HereGeocodePlaceApiRepository implements PlaceApiRepository<HereGeocodeResponse> {
    private static final String QUERY_URL_PREFIX = "https://geocode.search.hereapi.com/v1/geocode";

    @Value("${here.api.key}")
    private String apiKey;

    private final RestOperations restOperations;

    @Override
    public HereGeocodeResponse getResponse(String address) {
        try {
            final ResponseEntity<HereGeocodeResponse> response =
                    restOperations.getForEntity(constructQueryWithAddress(address), HereGeocodeResponse.class);

            if (!response.getStatusCode().equals(HttpStatus.OK) || response.getBody() == null) {
                throw new ApiException();
            }

            return response.getBody();
        }
        catch (RestClientException e) {
            throw new ApiException();
        }
    }

    public String constructQueryWithAddress(String address) {
        return QUERY_URL_PREFIX + "?apiKey=" + apiKey + "&q=" + address;
    }
}
