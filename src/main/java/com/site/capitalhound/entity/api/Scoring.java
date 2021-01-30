
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
    "queryScore",
    "fieldScore"
})
public class Scoring {

    @JsonProperty("queryScore")
    private Integer queryScore;
    @JsonProperty("fieldScore")
    @Valid
    private FieldScore fieldScore;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("queryScore")
    public Integer getQueryScore() {
        return queryScore;
    }

    @JsonProperty("queryScore")
    public void setQueryScore(Integer queryScore) {
        this.queryScore = queryScore;
    }

    @JsonProperty("fieldScore")
    public FieldScore getFieldScore() {
        return fieldScore;
    }

    @JsonProperty("fieldScore")
    public void setFieldScore(FieldScore fieldScore) {
        this.fieldScore = fieldScore;
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
