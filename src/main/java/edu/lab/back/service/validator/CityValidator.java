package edu.lab.back.service.validator;

import edu.lab.back.dtoPojos.request.CityRequestPojo;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface CityValidator {

    void validateSave(CityRequestPojo requestJson) throws InvalidPayloadException;

    void validateUpdate(CityRequestPojo requestJson) throws InvalidPayloadException;

}
