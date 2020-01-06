package edu.lab.back.controller;

import edu.lab.back.dtoPojos.request.CityRequestPojo;
import edu.lab.back.dtoPojos.response.CitiesResponsePojo;
import edu.lab.back.dtoPojos.response.CityResponsePojo;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.service.validator.CityValidator;
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

import java.util.List;

@RestController
@RequestMapping(CityController.CONTROLLER_BASE_URL)
public class CityController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/city";

    private final CityCrudService cityCrudService;

    private final CityValidator validator;

    @Autowired
    public CityController(@NonNull final CityCrudService cityCrudService, @NonNull final CityValidator validator) {
        this.cityCrudService = cityCrudService;
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
    public CityResponsePojo getCity(@PathVariable(value = "id") Long id) {
        final CityResponsePojo city = this.cityCrudService.getById(id);
        return city;
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public CitiesResponsePojo getAllCityes() {
        final CitiesResponsePojo result = new CitiesResponsePojo();

        final List<CityResponsePojo> cityes = this.cityCrudService.getAll();
        result.setCities(cityes);

        return result;
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
    public CityResponsePojo save(@RequestBody CityRequestPojo cityJson) throws InvalidPayloadException {
        this.validator.validateSave(cityJson);
        final CityResponsePojo saved = this.cityCrudService.save(cityJson);

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
        })
    public CityResponsePojo udpate(@RequestBody CityRequestPojo cityJson) throws InvalidPayloadException {
        this.validator.validateUpdate(cityJson);
        final CityResponsePojo updated = this.cityCrudService.update(cityJson);

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
    public CityResponsePojo delete(@PathVariable("id") Long id) throws InvalidPayloadException {
        final CityResponsePojo deleted = this.cityCrudService.deleteById(id);

        return deleted;
    }

}
