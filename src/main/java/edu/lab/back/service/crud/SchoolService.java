package edu.lab.back.service.crud;

import edu.lab.back.dtoPojos.request.SchoolRequestPojo;
import edu.lab.back.dtoPojos.response.SchoolResponsePojo;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface SchoolService extends BaseCrudService<SchoolRequestPojo, SchoolResponsePojo, Long> {

    List<SchoolResponsePojo> getSchoolsByCityId(Long cityId) throws InvalidPayloadException;

}
