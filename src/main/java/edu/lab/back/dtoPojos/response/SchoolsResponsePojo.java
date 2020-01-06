package edu.lab.back.dtoPojos.response;

import edu.lab.back.dtoPojos.XSLPojo;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "schools")
public class SchoolsResponsePojo implements XSLPojo {

    private List<SchoolResponsePojo> schools;

    @XmlElementWrapper(name = "schools_array")
    @XmlElement(name = "school")
    public List<SchoolResponsePojo> getSchools() {
        return schools;
    }

    @Override
    public String getTemplateName() {
        return "schools_template";
    }
}
