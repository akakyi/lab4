package edu.lab.back.dtoPojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.dtoPojos.XSLPojo;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
//@JacksonXmlRootElement(localName = "city")
@XmlRootElement(name = "city")
public class CityResponsePojo implements XSLPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    //TODO в рамках лабы норм, но лучше бы тут лежали просто айдишники
//    @JsonProperty(value = "schools")
//    private List<SchoolResponsePojo> schools;
    @JsonProperty(value = "schools_ids")
    private List<Long> schoolsIds;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElementWrapper(name = "school_ids")
    @XmlElement(name = "id")
    public List<Long> getSchoolsIds() {
        return schoolsIds;
    }

    public static CityResponsePojo convert(final CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }

        final CityResponsePojo cityResponsePojo = new CityResponsePojo();
        cityResponsePojo.setId(cityEntity.getId());
        cityResponsePojo.setName(cityEntity.getName());

        if (cityEntity.getSchools() != null) {
            final List<Long> schoolsIds = cityEntity.getSchools()
                .stream()
                .map(SchoolEntity::getId)
                .collect(Collectors.toList());

            cityResponsePojo.setSchoolsIds(schoolsIds);
        }

        return cityResponsePojo;
    }

    @Override
    public String getTemplateName() {
        return "city_template";
    }
}
