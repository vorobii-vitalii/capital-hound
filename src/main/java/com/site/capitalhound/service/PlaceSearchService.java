package com.site.capitalhound.service;

import com.site.capitalhound.entity.SearchPlaceResult;

public interface PlaceSearchService {
    SearchPlaceResult getPlaceByAddress(String address);
}
