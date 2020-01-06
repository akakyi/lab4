package edu.lab.back.service.validator.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.repositories.CityRepository;
import edu.lab.back.dtoPojos.request.SchoolRequestPojo;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.constants.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchoolValidatorImpl implements SchoolValidator {

    @NonNull
    private final CityRepository cityRepository;
    
    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    public void validateSave(final SchoolRequestPojo requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        if (requestJson.getId() != null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }

        this.baseValidate(requestJson);
    }

    @Override
    public void validateUpdate(final SchoolRequestPojo requestJson) throws InvalidPayloadException {
        if (requestJson == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        if (requestJson.getId() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }

        this.baseValidate(requestJson);
    }

    private void baseValidate(@NonNull final SchoolRequestPojo requestJson) throws InvalidPayloadException {
        final Long cityId = requestJson.getCityId();
        if (cityId == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
        final Optional<CityEntity> city = this.cityRepository.findById(cityId);
        if (!city.isPresent()) {
            throw new InvalidPayloadException(this.validationMessages.getReferredEntityNotExist());
        }

        if (requestJson.getName() == null) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        } else if (requestJson.getName().equals("")) {
            throw new InvalidPayloadException(this.validationMessages.getInvalidRequestJson());
        }
    }

}
