package edu.lab.back.controller;

import edu.lab.back.dtoPojos.response.SchoolResponsePojo;
import edu.lab.back.dtoPojos.response.SchoolsResponsePojo;
import edu.lab.back.service.crud.SchoolService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/school_of_city")
public class SchoolByCityController {

    private final SchoolService schoolService;

    public SchoolByCityController(@NonNull final SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    protected SchoolsResponsePojo getSchoolsByCity(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException {
        final SchoolsResponsePojo result = new SchoolsResponsePojo();

        final List<SchoolResponsePojo> schools = this.schoolService.getSchoolsByCityId(id);
        result.setSchools(schools);

        return result;
    }

}
