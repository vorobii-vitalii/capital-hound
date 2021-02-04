package com.site.capitalhound.dao;

public interface ElevationApiRepository<T> {
    T getResponseByCoordinate(double latitude, double longitude);
}
