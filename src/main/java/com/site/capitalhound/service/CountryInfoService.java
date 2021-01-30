package com.site.capitalhound.service;

import com.site.capitalhound.entity.CountryInfo;

public interface CountryInfoService {
    CountryInfo getCountryInfoByCountryCode(String countryCode);
}
