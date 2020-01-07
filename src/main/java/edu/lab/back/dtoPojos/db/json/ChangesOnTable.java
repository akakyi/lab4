package edu.lab.back.dtoPojos.db.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChangesOnTable {

    @JsonProperty(value = "changes")
    private List<ChangeOnField> changes;

}
