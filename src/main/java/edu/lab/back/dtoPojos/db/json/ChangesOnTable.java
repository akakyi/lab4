package edu.lab.back.dtoPojos.db.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChangesOnTable {

    @JsonProperty(value = "changes")
    private List<ChangeOnField> changes;

    public void addChange(final ChangeOnField changeOnField) {

        final Class<ChangesOnTable> changesOnTableClass = ChangesOnTable.class;
        final Method[] methods = changesOnTableClass.getMethods();
        for (Method m : methods) {
            m.getAnnotation(JsonProperty.class);
        }


        if (changeOnField != null) {
            if (this.changes == null) {
                this.changes = new ArrayList<>();
            }

            this.changes.add(changeOnField);
        }
    }

}
