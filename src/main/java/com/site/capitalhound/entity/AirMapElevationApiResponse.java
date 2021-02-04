package com.site.capitalhound.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirMapElevationApiResponse {
    private String status;
    private Integer[] data;
}
