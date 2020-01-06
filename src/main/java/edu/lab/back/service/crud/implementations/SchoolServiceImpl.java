package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.db.repositories.SchoolRepository;
import edu.lab.back.dtoPojos.request.SchoolRequestPojo;
import edu.lab.back.dtoPojos.response.SchoolResponsePojo;
import edu.lab.back.service.crud.SchoolService;
import edu.lab.back.util.constants.ValidationMessages;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl extends BaseCrudService<SchoolEntity, Long> implements SchoolService {

    @NonNull
    private final SchoolRepository schoolRepository;

    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    protected SchoolRepository getRepo() {
        return this.schoolRepository;
    }

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    public SchoolResponsePojo getById(final Long id) {
        final SchoolEntity school = this.getEntityById(id);
        final SchoolResponsePojo converted = SchoolResponsePojo.convert(school);
        return converted;
    }

    @Override
    public List<SchoolResponsePojo> getAll() {
        final Iterable<SchoolEntity> allSchools = this.getAllEntityes();
        final List<SchoolResponsePojo> result = StreamSupport.stream(allSchools.spliterator(), false)
            .map(SchoolResponsePojo::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public SchoolResponsePojo deleteById(@NonNull final Long id) {
        final SchoolEntity deletedEntity = this.deleteEntityById(id);

        final SchoolResponsePojo result = SchoolResponsePojo.convert(deletedEntity);
        return result;
    }

    @Override
    public SchoolResponsePojo save(@NonNull final SchoolRequestPojo schoolRequestJson) {
        final SchoolEntity entity = new SchoolEntity();
        entity.setId(schoolRequestJson.getId());
        entity.setName(schoolRequestJson.getName());

//        final CityEntity city = this.cityDao.getById(schoolRequestJson.getCityId(), CityEntity.class);
        final CityEntity city = new CityEntity();
        city.setId(schoolRequestJson.getCityId());
        entity.setCity(city);

        final SchoolEntity saved = this.schoolRepository.save(entity);
        final SchoolResponsePojo savedJson = SchoolResponsePojo.convert(saved);
        return savedJson;
    }

    @Override
    public SchoolResponsePojo update(@NonNull final SchoolRequestPojo schoolJson) {
        final Long schoolId = schoolJson.getId();
        final SchoolEntity school = this.getEntityById(schoolId);

        final CityEntity city = new CityEntity();
        city.setId(schoolJson.getCityId());
        school.setCity(city);

        school.setName(schoolJson.getName());

        final SchoolEntity updated = this.schoolRepository.save(school);
        final SchoolResponsePojo updatedJson = SchoolResponsePojo.convert(updated);
        return updatedJson;
    }

    @Override
    public List<SchoolResponsePojo> getSchoolsByCityId(@NonNull final Long cityId) {
        final List<SchoolEntity> schoolsByCityId = this.schoolRepository.getSchoolsByCityId(cityId);
        final List<SchoolResponsePojo> result = schoolsByCityId.stream()
            .map(SchoolResponsePojo::convert)
            .collect(Collectors.toList());

        return result;
    }
}
