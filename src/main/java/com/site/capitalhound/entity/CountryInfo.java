package com.site.capitalhound.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CountryInfo implements Serializable {
    private String name;
    private String capital;
    private String region;
    private int population;
    private double area;
    private String flag;
    private String[] borders;
}
