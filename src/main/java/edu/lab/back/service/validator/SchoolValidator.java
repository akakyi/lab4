package edu.lab.back.service.validator;

import edu.lab.back.dtoPojos.request.SchoolRequestPojo;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface SchoolValidator {

    void validateSave(SchoolRequestPojo requestJson) throws InvalidPayloadException;

    void validateUpdate(SchoolRequestPojo requestJson) throws InvalidPayloadException;

}

