package edu.lab.back.service.validator;

import edu.lab.back.dtoPojos.request.ProfileRequestPojo;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface ProfileValidator {

    void validateSave(ProfileRequestPojo requestJson) throws InvalidPayloadException;

    void validateUpdate(ProfileRequestPojo requestJson) throws InvalidPayloadException;

}
