package edu.lab.back.dtoPojos.response;

import edu.lab.back.dtoPojos.XSLPojo;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "profiles")
public class ProfilesResponsePojo implements XSLPojo {

    private List<ProfileResponsePojo> profiles;

    @XmlElementWrapper(name = "profiles_array")
    @XmlElement(name = "profile")
    public List<ProfileResponsePojo> getProfiles() {
        return profiles;
    }

    @Override
    public String getTemplateName() {
        return "profiles_template";
    }
}
