package com.site.capitalhound.dao;

public interface CountryInfoApiRepository<T> {
    T getByCountryCode(String countryCode);
}
