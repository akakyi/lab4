package edu.lab.back.dtoPojos.db.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ChangesOnTableJson {

    @JsonProperty(value = "changes")
    private List<ChangeOnField> changes;

    public void addChange(final ChangeOnField changeOnField) {
        final Class<ChangesOnTableJson> changesOnTableClass = ChangesOnTableJson.class;
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

    public ChangesOnTableJson fillByEntity(final Object entity) {
        final ChangesOnTableJson result = new ChangesOnTableJson();
        if (entity == null) {
            return result;
        }

        final Class<?> entityClass = entity.getClass();
        final Field[] fields = entityClass.getDeclaredFields();
        Arrays.stream(fields)
            .filter(f -> f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(JoinColumn.class))
            .forEach(f -> {
                try {
                    final ChangeOnField change = this.getChange(f, entity);
                    this.addChange(change);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            });

        return this;
    }

    private ChangeOnField getChange(final Field field, final Object entity) throws IllegalAccessException {
        final Column columnAnnotation = field.getAnnotation(Column.class);
        final JoinColumn joinColumnAnnotation = field.getAnnotation(JoinColumn.class);

        String fieldName;
        if (columnAnnotation != null) {
            fieldName = columnAnnotation.name();
        } else {
            fieldName = joinColumnAnnotation.name();
        }

        field.setAccessible(true);
        Object fieldValue = field.get(entity);

        final ChangeOnField result = new ChangeOnField();
        result.setFieldName(fieldName);
        result.setFieldNewValue(fieldValue.toString());

        return result;
    }

}
