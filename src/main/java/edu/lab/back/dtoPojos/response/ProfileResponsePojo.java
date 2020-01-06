package edu.lab.back.dtoPojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.dtoPojos.XSLPojo;
import edu.lab.back.util.ProfileTypeEnum;
import edu.lab.back.util.converters.adapters.ProfileTypeAdapter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "profile")
public class ProfileResponsePojo implements XSLPojo {

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

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "age")
    public Integer getAge() {
        return age;
    }

    @XmlElement(name = "profile_type")
    @XmlJavaTypeAdapter(ProfileTypeAdapter.class)
    public ProfileTypeEnum getProfileType() {
        return profileType;
    }

    @XmlElement(name = "class_level")
    public String getClassLevel() {
        return classLevel;
    }

    public static ProfileResponsePojo convert(final ProfileEntity profileEntity) {
        if (profileEntity == null) {
            return null;
        }

        final ProfileResponsePojo result = new ProfileResponsePojo();
        result.setAge(profileEntity.getAge());
        result.setClassLevel(profileEntity.getClassLevel());
        result.setId(profileEntity.getId());
        result.setName(profileEntity.getName());

        final ProfileTypeEntity profileType = profileEntity.getProfileType();
        if (profileType != null) {
            result.setProfileType(ProfileTypeEnum.getEnumByName(profileType.getName()));
        }

        return result;
    }

    @Override
    public String getTemplateName() {
        return "profile_template";
    }
}
