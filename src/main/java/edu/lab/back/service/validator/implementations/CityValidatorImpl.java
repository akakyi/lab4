package edu.lab.back.service.validator.implementations;

import edu.lab.back.dtoPojos.request.CityRequestPojo;
import edu.lab.back.service.validator.CityValidator;
import edu.lab.back.util.constants.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityValidatorImpl implements CityValidator {
    
    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    public void validateSave(final CityRequestPojo requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        if (requestJson.getId() != null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }

        this.baseValidation(requestJson);
    }

    @Override
    public void validateUpdate(final CityRequestPojo requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        if (requestJson.getId() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }

        this.baseValidation(requestJson);
    }

    private void baseValidation(@NonNull final CityRequestPojo requestJson) throws InvalidPayloadException {
        if (requestJson.getName() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        } else if (requestJson.getName().equals("")) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
    }
}
