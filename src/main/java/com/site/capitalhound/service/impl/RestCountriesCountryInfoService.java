package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.CountryInfoApiRepository;
import com.site.capitalhound.entity.CountryInfo;
import com.site.capitalhound.service.CountryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestCountriesCountryInfoService implements CountryInfoService {
    private final CountryInfoApiRepository<CountryInfo> countryInfoApiRepository;

    @Override
    public CountryInfo getCountryInfoByCountryCode(String countryCode) {
        return countryInfoApiRepository.getByCountryCode(countryCode);
    }

}
