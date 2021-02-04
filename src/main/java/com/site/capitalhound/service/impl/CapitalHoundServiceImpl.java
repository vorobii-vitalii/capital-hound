package com.site.capitalhound.service.impl;

import com.site.capitalhound.entity.CapitalHoundResult;
import com.site.capitalhound.entity.CountryInfo;
import com.site.capitalhound.entity.SearchPlaceResult;
import com.site.capitalhound.service.CapitalHoundService;
import com.site.capitalhound.service.CountryInfoService;
import com.site.capitalhound.service.ElevationService;
import com.site.capitalhound.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class CapitalHoundServiceImpl implements CapitalHoundService {
    private final PlaceSearchService placeSearchService;
    private final CountryInfoService countryInfoService;
    private final ElevationService   elevationService;

    @Override
    public CapitalHoundResult getResultByProvidedAddress(String inputAddress) {
        final var placeByAddressInfo =
                placeSearchService.getPlaceByAddress(inputAddress);

        final String associatedCountryCode = placeByAddressInfo.getCountryCode();

        final var countryInfo =
                countryInfoService.getCountryInfoByCountryCode(associatedCountryCode);

        final var capitalInfo =
                placeSearchService.getPlaceByAddress(countryInfo.getCapital());

        final var capitalElevationLevel =
                elevationService.getByCoordinates(capitalInfo.getPlaceLatitude(), capitalInfo.getPlaceLongitude());

        return mergeResults(placeByAddressInfo, countryInfo, capitalInfo, capitalElevationLevel);
    }

    private static CapitalHoundResult mergeResults(
            SearchPlaceResult placeByAddressInfo,
            CountryInfo countryInfo,
            SearchPlaceResult capitalInfo,
            Integer capitalElevationLevel) {

        return CapitalHoundResult.builder()
                .region(countryInfo.getRegion())
                .countryName(countryInfo.getName())
                .flagUrl(countryInfo.getFlag())
                .capitalName(countryInfo.getCapital())
                .enteredAddressLatitude(placeByAddressInfo.getPlaceLatitude())
                .enteredAddressLongitude(placeByAddressInfo.getPlaceLongitude())
                .associatedCapitalLatitude(capitalInfo.getPlaceLatitude())
                .associatedCapitalLongitude(capitalInfo.getPlaceLongitude())
                .area(countryInfo.getArea())
                .population(countryInfo.getPopulation())
                .capitalElevationLevel(capitalElevationLevel)
                .build();
    }

    private List<CountryInfo> getNearbyCountriesResults(String[] borderCountries) {
        final var executorService = Executors.newFixedThreadPool(borderCountries.length);

        final List<CountryInfo> nearbyCountriesInfo = new ArrayList<>();

        try {
            List<Future<CountryInfo>> borderCountriesFutures = executorService.invokeAll(
                Stream
                    .of(borderCountries)
                    .map(this::getFutureFromCountryCode)
                    .collect(Collectors.toList())
            );

            for (int i = 0; i < borderCountries.length; i++) {
                try {
                    nearbyCountriesInfo.add(borderCountriesFutures.get(i).get());
                }
                catch (InterruptedException | ExecutionException e) {
                    log.error("Failed to fetch info about {}", borderCountries[i]);
                }
            }
        }
        catch (InterruptedException e) {
            log.error("Interrupted while fetching information about border countries");
        }

        return nearbyCountriesInfo;
    }

    private Callable<CountryInfo> getFutureFromCountryCode(String country) {
        return new Callable<CountryInfo>() {
            @Override
            public CountryInfo call() throws Exception {
                return countryInfoService.getCountryInfoByCountryCode(country);
            }
        };
    }

}
