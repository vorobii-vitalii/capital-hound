package com.site.capitalhound.service;

import com.site.capitalhound.entity.CapitalHoundResult;

public interface CapitalHoundService {
    CapitalHoundResult getResultByProvidedAddress(String inputAddress);
}
