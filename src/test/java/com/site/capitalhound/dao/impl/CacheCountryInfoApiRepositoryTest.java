package com.site.capitalhound.dao.impl;

import com.site.capitalhound.dao.CountryInfoApiRepository;
import com.site.capitalhound.entity.CountryInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CacheCountryInfoApiRepositoryTest {

    @MockBean
    private CountryInfoApiRepository<CountryInfo> countryInfoApiRepositoryMock;

    private CacheCountryInfoApiRepository cacheCountryInfoApiRepository;

    @BeforeEach
    public void init() {
        cacheCountryInfoApiRepository = new CacheCountryInfoApiRepository(countryInfoApiRepositoryMock);
    }

    @Test
    public void test_GivenSameCountryCodeTwice_VerifyCacheFeature() {
        var countryCode = "UA";

        var countryInfo1 = CountryInfo.builder()
                .area(244.0)
                .capital("Capital 1")
                .name("Country 1")
                .region("Region 1")
                .build();

        var countryInfo2 = CountryInfo.builder()
                .area(2120.0)
                .capital("Capital 2")
                .name("Country 2")
                .region("Region 2")
                .build();

        when(countryInfoApiRepositoryMock.getByCountryCode(countryCode))
                .thenReturn(countryInfo1, countryInfo2);

        var result1 = cacheCountryInfoApiRepository.getByCountryCode(countryCode);
        var result2 = cacheCountryInfoApiRepository.getByCountryCode(countryCode);

        assertEquals(result1, countryInfo1);
        assertEquals(result1, result2);
    }

}