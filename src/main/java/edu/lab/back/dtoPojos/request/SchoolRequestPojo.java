package edu.lab.back.dtoPojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.dtoPojos.DTOPojo;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "school")
public class SchoolRequestPojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "city_id")
    private Long cityId;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

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
