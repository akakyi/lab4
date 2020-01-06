package edu.lab.back.service.crud;

import edu.lab.back.dtoPojos.request.ProfileRequestPojo;
import edu.lab.back.dtoPojos.response.ProfileResponsePojo;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface ProfileService extends BaseCrudService<ProfileRequestPojo, ProfileResponsePojo, Long> {

    List<ProfileResponsePojo> getProfileBySchoolId(Long schoolId) throws InvalidPayloadException;

}
