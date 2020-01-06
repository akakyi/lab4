package edu.lab.back.service.crud;

import edu.lab.back.dtoPojos.DTOPojo;
import edu.lab.back.util.exception.ResourceNotFoundException;

import java.util.List;

public interface BaseCrudService<RequestJsonType extends DTOPojo, ResponseJsonType extends DTOPojo, Id> {

    ResponseJsonType getById(Id id) throws ResourceNotFoundException;

    List<ResponseJsonType> getAll();

    ResponseJsonType deleteById(Id id) throws ResourceNotFoundException;

    ResponseJsonType save(RequestJsonType json);

    ResponseJsonType update(RequestJsonType json);

}
