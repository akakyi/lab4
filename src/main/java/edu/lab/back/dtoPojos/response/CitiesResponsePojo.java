package edu.lab.back.dtoPojos.response;

import edu.lab.back.dtoPojos.XSLPojo;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "cities")
public class CitiesResponsePojo implements XSLPojo {

    private List<CityResponsePojo> cities;

    @XmlElementWrapper(name = "cities_array")
    @XmlElement(name = "city")
    public List<CityResponsePojo> getCities() {
        return cities;
    }

    @Override
    public String getTemplateName() {
        return "cities_template";
    }
}
