package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.PlaceApiRepository;
import com.site.capitalhound.entity.SearchPlaceResult;
import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.exception.NotFoundException;
import com.site.capitalhound.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Place search implementation using HERE API
 */
@Service
@RequiredArgsConstructor
public class HerePlaceSearchServiceImpl implements PlaceSearchService {
    private final PlaceApiRepository<HereGeocodeResponse> placeApiRepository;

    @Override
    public SearchPlaceResult getPlaceByAddress(String address) {
        final HereGeocodeResponse response = placeApiRepository.getResponse(address);

        return retrievePlaceFromResponse(response);
    }

    /**
     * The underlying api provides places list in sorted way by relevance DESC,
     * Consequently, it's a good idea to respond using the first item in a list if it's present
     */
    private SearchPlaceResult retrievePlaceFromResponse(HereGeocodeResponse hereGeocode) {

        if (hereGeocode.getItems() == null || hereGeocode.getItems().isEmpty()) {
            throw new NotFoundException();
        }

        final var item = hereGeocode.getItems().get(0);

        final String countryCode = item.getAddress().getCountryCode();
        final Double latitude = item.getPosition().getLat();
        final Double longitude = item.getPosition().getLng();

        return SearchPlaceResult.builder()
                    .placeLatitude(latitude)
                    .placeLongitude(longitude)
                    .countryCode(countryCode)
                    .build();
    }

}
