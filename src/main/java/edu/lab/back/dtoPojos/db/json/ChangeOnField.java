package edu.lab.back.dtoPojos.db.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeOnField {

    @JsonProperty(value = "field_name")
    private String fieldName;

    @JsonProperty(value = "field_new_value")
    private String fieldNewValue;

}
