package com.site.capitalhound.service.impl;

import com.site.capitalhound.dao.PlaceApiRepository;
import com.site.capitalhound.entity.api.Address;
import com.site.capitalhound.entity.api.HereGeocodeResponse;
import com.site.capitalhound.entity.api.Item;
import com.site.capitalhound.entity.api.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class HerePlaceSearchServiceImplTest {

    @MockBean
    private PlaceApiRepository<HereGeocodeResponse> placeApiRepositoryMock;

    @Autowired
    private HerePlaceSearchServiceImpl herePlaceSearchService;

    @Test
    public void test_GivenAddress_ThenProduceCorrectResult() {
        var inputAddress = "Ivano-Frankivsk";

        var result = new HereGeocodeResponse();

        var item = new Item();
        var address = new Address();
        address.setCountryCode("UA");
        var position = new Position();
        position.setLng((double) 1);
        position.setLat((double) 2);
        item.setAddress(address);
        item.setPosition(position);

        result.setItems(List.of(item));

        when(placeApiRepositoryMock.getResponse(inputAddress))
                .thenReturn(result);

        var placeResult = herePlaceSearchService.getPlaceByAddress(inputAddress);

        assertEquals(placeResult.getPlaceLatitude(),  2);
        assertEquals(placeResult.getPlaceLongitude(),  1);
        assertEquals(placeResult.getCountryCode(), "UA");
    }


}