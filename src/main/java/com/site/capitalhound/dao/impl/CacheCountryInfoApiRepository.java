package com.site.capitalhound.dao.impl;

import com.site.capitalhound.dao.CountryInfoApiRepository;
import com.site.capitalhound.entity.CountryInfo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CacheCountryInfoApiRepository implements CountryInfoApiRepository<CountryInfo> {
    private final Map<String, CountryInfo> countryInfoCache = new HashMap<>();
    private final CountryInfoApiRepository<CountryInfo> countryInfoApiRepository;

    @Override
    public CountryInfo getByCountryCode(String countryCode) {

        if (!countryInfoCache.containsKey(countryCode)) {
            countryInfoCache.put(countryCode, countryInfoApiRepository.getByCountryCode(countryCode));
        }

        return countryInfoCache.get(countryCode);
    }

}
