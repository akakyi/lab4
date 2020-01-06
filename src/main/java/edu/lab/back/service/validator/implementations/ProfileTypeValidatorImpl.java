package edu.lab.back.service.validator.implementations;

import edu.lab.back.dtoPojos.ProfileTypePojo;
import edu.lab.back.service.validator.ProfileTypeValidator;
import edu.lab.back.util.constants.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileTypeValidatorImpl implements ProfileTypeValidator {

    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    public void validateSave(final ProfileTypePojo profileTypeJson) throws InvalidPayloadException {
        this.baseValidate(profileTypeJson);
    }

    @Override
    public void validateUpdate(final ProfileTypePojo profileTypeJson) throws InvalidPayloadException {
        this.baseValidate(profileTypeJson);
    }

    private void baseValidate(@NonNull final ProfileTypePojo profileTypeJson) throws InvalidPayloadException {
        if (profileTypeJson.getId() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        if (profileTypeJson.getName() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
    }
}
