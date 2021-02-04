package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.ElevationApiRepository;
import com.site.capitalhound.entity.AirMapElevationApiResponse;
import com.site.capitalhound.service.ElevationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirMapElevationService implements ElevationService {
    private final ElevationApiRepository<AirMapElevationApiResponse> elevationApiRepository;

    @Override
    public Integer getByCoordinates(Double latitude, Double longitude) {
        var response =
                elevationApiRepository.getResponseByCoordinate(latitude, longitude);

        return response.getData()[0];
    }

}
