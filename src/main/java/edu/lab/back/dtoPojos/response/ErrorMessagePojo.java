package edu.lab.back.dtoPojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.dtoPojos.XSLPojo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "error")
public class ErrorMessagePojo implements XSLPojo {

    @JsonProperty(value = "message")
    @XmlElement(name = "message")
    private String message;

    @XmlTransient
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getTemplateName() {
        return "error_template";
    }
}
