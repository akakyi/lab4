package edu.lab.back.dtoPojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.dtoPojos.DTOPojo;
import edu.lab.back.util.ProfileTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileRequestPojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "profile_type")
    private ProfileTypeEnum profileType;

    @JsonProperty(value = "class_level")
    private String classLevel;

    @JsonProperty(value = "school_id")
    private Long schoolId;

}
