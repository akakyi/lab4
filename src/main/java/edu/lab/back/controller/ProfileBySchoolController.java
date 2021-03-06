package edu.lab.back.controller;

import edu.lab.back.dtoPojos.response.ProfileResponsePojo;
import edu.lab.back.dtoPojos.response.ProfilesResponsePojo;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles_of_school")
@RequiredArgsConstructor
public class ProfileBySchoolController {

    @NonNull
    private final ProfileService profileService;

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        })
    public ProfilesResponsePojo getProfilesBySchool(
        @PathVariable("id") Long id
    ) throws InvalidPayloadException
    {
        final ProfilesResponsePojo result = new ProfilesResponsePojo();

        final List<ProfileResponsePojo> profiles = this.profileService.getProfileBySchoolId(id);
        result.setProfiles(profiles);

        return result;
    }

}
