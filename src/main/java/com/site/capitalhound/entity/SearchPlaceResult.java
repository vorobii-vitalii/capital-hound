package com.site.capitalhound.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class SearchPlaceResult implements Serializable {
    private String countryCode;
    private Double placeLongitude;
    private Double placeLatitude;
}
