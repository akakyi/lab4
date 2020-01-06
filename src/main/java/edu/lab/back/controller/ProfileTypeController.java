package edu.lab.back.controller;

import edu.lab.back.dtoPojos.ProfileTypePojo;
import edu.lab.back.dtoPojos.ProfileTypesResponsePojo;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.util.constants.UrlPatterns;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileTypeController.CONTROLLER_BASE_URL)
@RequiredArgsConstructor
public class ProfileTypeController {

    public final static String CONTROLLER_BASE_URL = UrlPatterns.CRUD_BASE_URL + "/profile_type";

    @NonNull
    private final ProfileTypeCrudService profileTypeCrudService;

    @RequestMapping(
        value = "/{id}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ProfileTypePojo getById(
        @PathVariable(value = "id") final Integer id
    ) {
        final ProfileTypePojo profileType = this.profileTypeCrudService.getById(id);

        return profileType;
    }

    @RequestMapping(
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ProfileTypesResponsePojo getAll() {
        final ProfileTypesResponsePojo result = new ProfileTypesResponsePojo();

        final List<ProfileTypePojo> types = this.profileTypeCrudService.getAll();
        result.setTypes(types);

        return result;
    }

}
