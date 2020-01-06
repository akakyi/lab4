package edu.lab.back.dtoPojos;

import edu.lab.back.dtoPojos.response.CityResponsePojo;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "profile_types")
public class ProfileTypesResponsePojo implements XSLPojo {

    private List<ProfileTypePojo> types;

    @XmlElementWrapper(name = "types_array")
    @XmlElement(name = "type")
    public List<ProfileTypePojo> getTypes() {
        return types;
    }

    @Override
    public String getTemplateName() {
        return "types_template";
    }
}
