package edu.lab.back.dtoPojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlTransient;

public interface XSLPojo extends DTOPojo {

    @XmlTransient
    @JsonIgnore
    String getTemplateName();

}
