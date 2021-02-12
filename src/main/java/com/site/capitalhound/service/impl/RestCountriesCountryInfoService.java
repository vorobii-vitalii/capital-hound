package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.CountryInfoApiRepository;
import com.site.capitalhound.dao.impl.CacheCountryInfoApiRepository;
import com.site.capitalhound.entity.CountryInfo;
import com.site.capitalhound.service.CountryInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class RestCountriesCountryInfoService implements CountryInfoService {
    private CountryInfoApiRepository<CountryInfo> countryInfoApiRepository;

    @PostConstruct
    private void attachProxy() {
        countryInfoApiRepository =
                new CacheCountryInfoApiRepository(countryInfoApiRepository);
    }

    @Override
    public CountryInfo getCountryInfoByCountryCode(String countryCode) {
        return countryInfoApiRepository.getByCountryCode(countryCode);
    }

}
