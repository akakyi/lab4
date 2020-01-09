package edu.lab.back.dtoPojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.dtoPojos.DTOPojo;
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

    @XmlElement(name = "school_id")
    public Long getSchoolId() {
        return schoolId;
    }
}
