package edu.lab.back.service.crud;

import edu.lab.back.dtoPojos.DTOPojo;
import edu.lab.back.util.exception.ResourceNotFoundException;

import java.util.List;

public interface BaseCrudComponent<RequestPojoType extends DTOPojo, ResponsePojoType extends DTOPojo, Id> {

    ResponsePojoType getById(Id id) throws ResourceNotFoundException;

    List<ResponsePojoType> getAll();

    ResponsePojoType deleteById(Id id) throws ResourceNotFoundException;

    ResponsePojoType save(RequestPojoType json);

    ResponsePojoType update(RequestPojoType json);

}
