package com.site.capitalhound.dao.impl;

import com.site.capitalhound.dao.ElevationApiRepository;
import com.site.capitalhound.entity.AirMapElevationApiResponse;
import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@Repository
@RequiredArgsConstructor
public class AirMapElevationApiRepository implements ElevationApiRepository<AirMapElevationApiResponse> {
    private static final String API_URL_PREFIX = "https://api.airmap.com/elevation/v1/ele/";
    private static final String SUCCESS_STATUS = "success";

    @Value("${airmap.api.key}")
    private String airMapApiKey;

    private final RestOperations restOperations;

    @Override
    public AirMapElevationApiResponse getResponseByCoordinate(double latitude, double longitude) {
        try {
            final ResponseEntity<AirMapElevationApiResponse> response =
                    restOperations.getForEntity(
                            constructQueryWithCoordinate(latitude, longitude),
                            AirMapElevationApiResponse.class);

            if (!response.getStatusCode().equals(HttpStatus.OK)
                    || response.getBody() == null
                    || !response.getBody().getStatus().equalsIgnoreCase(SUCCESS_STATUS)
            ) {
                throw new ApiException();
            }

            return response.getBody();
        }
        catch (RestClientException e) {
            throw new ApiException();
        }
    }

    private String constructQueryWithCoordinate(double latitude, double longitude) {
        return API_URL_PREFIX + "?points=" + latitude + "," + longitude;
    }

}
