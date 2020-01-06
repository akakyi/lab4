package edu.lab.back.controller;

import edu.lab.back.dtoPojos.request.SchoolRequestPojo;
import edu.lab.back.dtoPojos.response.SchoolResponsePojo;
import edu.lab.back.service.crud.SchoolService;
import edu.lab.back.service.validator.SchoolValidator;
import edu.lab.back.util.constants.UrlPatterns;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SchoolController.CONTROLLER_BASE_URL)
public class SchoolController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/school";

    private final SchoolService schoolService;

    private final SchoolValidator validator;

    @Autowired
    public SchoolController(
        @NonNull final SchoolService schoolService,
        @NonNull final SchoolValidator validator
    ) {
        this.schoolService = schoolService;
        this.validator = validator;
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    protected SchoolResponsePojo getSchool(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException {
        final SchoolResponsePojo school = this.schoolService.getById(id);

        return school;
    }

    @RequestMapping(
        method = RequestMethod.POST,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        },
        consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    protected SchoolResponsePojo save(@RequestBody SchoolRequestPojo schoolRequestJson) throws InvalidPayloadException {
        this.validator.validateSave(schoolRequestJson);
        final SchoolResponsePojo saved = this.schoolService.save(schoolRequestJson);

        return saved;
    }

    @RequestMapping(
        method = RequestMethod.PUT,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        },
        consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    protected SchoolResponsePojo update(
        @RequestBody SchoolRequestPojo schoolRequestJson
    ) throws InvalidPayloadException {
        this.validator.validateUpdate(schoolRequestJson);
        final SchoolResponsePojo updated = this.schoolService.update(schoolRequestJson);

        return updated;
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.DELETE,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    protected SchoolResponsePojo delete(@PathVariable("id") Long id) {
        final SchoolResponsePojo deleted = this.schoolService.deleteById(id);

        return deleted;
    }
}
