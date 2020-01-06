package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.repositories.CityRepository;
import edu.lab.back.dtoPojos.request.CityRequestPojo;
import edu.lab.back.dtoPojos.response.CityResponsePojo;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.util.constants.ValidationMessages;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CityCrudServiceImpl extends BaseCrudService<CityEntity, Long> implements CityCrudService {

    @NonNull
    private final CityRepository cityRepository;

    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    protected CityRepository getRepo() {
        return this.cityRepository;
    }

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    public CityResponsePojo getById(@NonNull final Long id) {
        final CityEntity city = this.getEntityById(id);
        final CityResponsePojo cityResponsePojo = CityResponsePojo.convert(city);

        return cityResponsePojo;
    }

    @Override
    public List<CityResponsePojo> getAll() {
        final Iterable<CityEntity> allCities = this.getAllEntityes();
        final List<CityResponsePojo> allCitiesJson = StreamSupport.stream(allCities.spliterator(), false)
            .map(CityResponsePojo::convert)
            .collect(Collectors.toList());

        return allCitiesJson;
    }

    @Override
    public CityResponsePojo deleteById(@NonNull final Long idString) {
        final CityEntity city = this.deleteEntityById(idString);
        final CityResponsePojo deletedJson = CityResponsePojo.convert(city);

        return deletedJson;
    }

    @Override
    public CityResponsePojo save(@NonNull final CityRequestPojo cityJson) {
        final CityEntity cityEntity = CityEntity.convert(cityJson);

        final CityEntity saved = this.cityRepository.save(cityEntity);
        final CityResponsePojo result = CityResponsePojo.convert(saved);

        return result;
    }

    @Override
    public CityResponsePojo update(@NonNull final CityRequestPojo cityJson) {
        final Long cityId = cityJson.getId();
        final CityEntity entity = this.getEntityById(cityId);

        entity.setName(cityJson.getName());

        final CityEntity added = this.cityRepository.save(entity);
        final CityResponsePojo result = CityResponsePojo.convert(added);

        return result;
    }

}
