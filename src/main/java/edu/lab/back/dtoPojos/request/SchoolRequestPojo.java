package edu.lab.back.dtoPojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.dtoPojos.DTOPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchoolRequestPojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "city_id")
    private Long cityId;

    public static SchoolRequestPojo convert(final SchoolEntity schoolEntity) {
        if (schoolEntity == null) {
            return null;
        }

        SchoolRequestPojo result = new SchoolRequestPojo();
        result.setId(schoolEntity.getId());
        result.setName(schoolEntity.getName());

        return result;
    }

}
