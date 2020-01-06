package edu.lab.back.controller;

import edu.lab.back.dtoPojos.request.ProfileRequestPojo;
import edu.lab.back.dtoPojos.response.ProfileResponsePojo;
import edu.lab.back.dtoPojos.response.ProfilesResponsePojo;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.service.validator.ProfileValidator;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(ProfileController.CONTROLLER_BASE_URL)
public class ProfileController {

    public static final String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile";

    @NonNull
    private final ProfileService profileService;

    @NotNull
    private final ProfileValidator profileValidator;

    @Autowired
    public ProfileController(
        @NonNull final ProfileService profileService,
        @NonNull final ProfileValidator profileValidator
    ) {
        this.profileService = profileService;
        this.profileValidator = profileValidator;
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ProfileResponsePojo getProfile(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException {
        final ProfileResponsePojo profile = this.profileService.getById(id);

        return profile;
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ProfilesResponsePojo getAllProfiles() {
        final ProfilesResponsePojo result = new ProfilesResponsePojo();

        final List<ProfileResponsePojo> profiles = this.profileService.getAll();
        result.setProfiles(profiles);

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
    public ProfileResponsePojo save(@RequestBody ProfileRequestPojo profileJson) throws InvalidPayloadException {
        this.profileValidator.validateSave(profileJson);
        final ProfileResponsePojo saved = this.profileService.save(profileJson);

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
    public ProfileResponsePojo update(@RequestBody ProfileRequestPojo profileJson) throws InvalidPayloadException {
        this.profileValidator.validateUpdate(profileJson);
        final ProfileResponsePojo updated = this.profileService.update(profileJson);

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
    public ProfileResponsePojo delete(@PathVariable("id") Long id) throws InvalidPayloadException {
        final ProfileResponsePojo deleted = this.profileService.deleteById(id);

        return deleted;
    }
}
