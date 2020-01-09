package edu.lab.back.dtoPojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ChangeTypeEntity;
import edu.lab.back.util.ChangeTypeEnum;
import lombok.Data;

@Data
public class ChangeTypePojo {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private String name;

    public static ChangeTypePojo convert(final ChangeTypeEntity changeTypeEntity) {
        if (changeTypeEntity == null) {
            return null;
        }

        final ChangeTypePojo result = new ChangeTypePojo();
        result.setId(changeTypeEntity.getId());
        result.setName(changeTypeEntity.getName());

        return result;
    }

    public static ChangeTypePojo getByEnum(final ChangeTypeEnum typeEnum) {
        if (typeEnum == null) {
            return null;
        }

        final ChangeTypePojo result = new ChangeTypePojo();
        result.setId(typeEnum.getId());
        result.setName(typeEnum.getName());

        return result;
    }

}
