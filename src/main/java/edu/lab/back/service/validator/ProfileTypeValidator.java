package edu.lab.back.service.validator;

import edu.lab.back.dtoPojos.ProfileTypePojo;
import edu.lab.back.util.exception.InvalidPayloadException;

public interface ProfileTypeValidator {

    void validateSave(ProfileTypePojo profileTypeJson) throws InvalidPayloadException;

    void validateUpdate(ProfileTypePojo profileTypeJson) throws InvalidPayloadException;

}
