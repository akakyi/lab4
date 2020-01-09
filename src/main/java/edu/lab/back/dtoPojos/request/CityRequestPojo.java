package edu.lab.back.dtoPojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.dtoPojos.DTOPojo;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "city")
public class CityRequestPojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public static CityRequestPojo convert(final CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }

        final CityRequestPojo cityJson = new CityRequestPojo();
        cityJson.setId(cityEntity.getId());
        cityJson.setName(cityEntity.getName());

        return cityJson;
    }
}
