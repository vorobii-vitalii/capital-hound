
package com.site.capitalhound.entity.api;

import java.util.HashMap;
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
    "west",
    "south",
    "east",
    "north"
})
public class MapView {

    @JsonProperty("west")
    private Double west;
    @JsonProperty("south")
    private Double south;
    @JsonProperty("east")
    private Double east;
    @JsonProperty("north")
    private Double north;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("west")
    public Double getWest() {
        return west;
    }

    @JsonProperty("west")
    public void setWest(Double west) {
        this.west = west;
    }

    @JsonProperty("south")
    public Double getSouth() {
        return south;
    }

    @JsonProperty("south")
    public void setSouth(Double south) {
        this.south = south;
    }

    @JsonProperty("east")
    public Double getEast() {
        return east;
    }

    @JsonProperty("east")
    public void setEast(Double east) {
        this.east = east;
    }

    @JsonProperty("north")
    public Double getNorth() {
        return north;
    }

    @JsonProperty("north")
    public void setNorth(Double north) {
        this.north = north;
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
