
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
    "city",
    "streets",
    "houseNumber"
})
public class FieldScore {

    @JsonProperty("city")
    private Integer city;
    @JsonProperty("streets")
    @Valid
    private List<Integer> streets = null;
    @JsonProperty("houseNumber")
    private Integer houseNumber;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("city")
    public Integer getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Integer city) {
        this.city = city;
    }

    @JsonProperty("streets")
    public List<Integer> getStreets() {
        return streets;
    }

    @JsonProperty("streets")
    public void setStreets(List<Integer> streets) {
        this.streets = streets;
    }

    @JsonProperty("houseNumber")
    public Integer getHouseNumber() {
        return houseNumber;
    }

    @JsonProperty("houseNumber")
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
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
