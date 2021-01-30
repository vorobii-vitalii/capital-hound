
package com.site.capitalhound.entity.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "id",
    "resultType",
    "houseNumberType",
    "address",
    "position",
    "access",
    "mapView",
    "scoring"
})
public class Item {

    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private String id;
    @JsonProperty("resultType")
    private String resultType;
    @JsonProperty("houseNumberType")
    private String houseNumberType;
    @JsonProperty("address")
    @Valid
    private Address address;
    @JsonProperty("position")
    @Valid
    private Position position;
    @JsonProperty("access")
    @Valid
    private List<Access> access = null;
    @JsonProperty("mapView")
    @Valid
    private MapView mapView;
    @JsonProperty("scoring")
    @Valid
    private Scoring scoring;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("resultType")
    public String getResultType() {
        return resultType;
    }

    @JsonProperty("resultType")
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @JsonProperty("houseNumberType")
    public String getHouseNumberType() {
        return houseNumberType;
    }

    @JsonProperty("houseNumberType")
    public void setHouseNumberType(String houseNumberType) {
        this.houseNumberType = houseNumberType;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("position")
    public Position getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Position position) {
        this.position = position;
    }

    @JsonProperty("access")
    public List<Access> getAccess() {
        return access;
    }

    @JsonProperty("access")
    public void setAccess(List<Access> access) {
        this.access = access;
    }

    @JsonProperty("mapView")
    public MapView getMapView() {
        return mapView;
    }

    @JsonProperty("mapView")
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    @JsonProperty("scoring")
    public Scoring getScoring() {
        return scoring;
    }

    @JsonProperty("scoring")
    public void setScoring(Scoring scoring) {
        this.scoring = scoring;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
