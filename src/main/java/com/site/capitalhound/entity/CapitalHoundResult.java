package com.site.capitalhound.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CapitalHoundResult {
    private String region;
    private String countryName;
    private String flagUrl;
    private String capitalName;
    private Double enteredAddressLongitude;
    private Double enteredAddressLatitude;
    private Double associatedCapitalLongitude;
    private Double associatedCapitalLatitude;
    private Integer population;
    private Double area;
    private Integer capitalElevationLevel;
}
