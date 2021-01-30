package com.site.capitalhound.dao;

public interface PlaceApiRepository<T> {
    T getResponse(String address);
}
