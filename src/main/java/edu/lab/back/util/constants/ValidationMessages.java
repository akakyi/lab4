package edu.lab.back.util.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ValidationMessages {

    @Value( "${validation.json.invalid_request}" )
    private String invalidRequestJson;

    @Value( "${validation.path.invalid_request}" )
    private String invalidPathVariable;

    @Value( "${validation.db.entity_not_exist}" )
    private String referredEntityNotExist;

    @Value( "${validation.db.contrain_violation}" )
    private String contrainViolation;

}
