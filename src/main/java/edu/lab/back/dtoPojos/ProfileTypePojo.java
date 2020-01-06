package edu.lab.back.dtoPojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ProfileTypeEntity;
import lombok.Data;

@Data
public class ProfileTypePojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private String name;

    public static ProfileTypePojo convert(final ProfileTypeEntity profileTypeEntity) {
        if (profileTypeEntity == null) {
            return null;
        }

        final ProfileTypePojo profileTypeJson = new ProfileTypePojo();
        profileTypeJson.setId(profileTypeEntity.getId());
        profileTypeJson.setName(profileTypeEntity.getName());

        return profileTypeJson;
    }

}
