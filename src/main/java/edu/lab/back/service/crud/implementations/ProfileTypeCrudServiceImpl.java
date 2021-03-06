package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.db.repositories.ProfileTypeRepository;
import edu.lab.back.dtoPojos.ProfileTypePojo;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.service.jms.JmsMessageSender;
import edu.lab.back.util.ChangeTypeEnum;
import edu.lab.back.util.constants.ValidationMessages;
import edu.lab.back.util.exception.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProfileTypeCrudServiceImpl
    extends BaseCrudService<ProfileTypeEntity, Integer>
    implements ProfileTypeCrudService
{

    @NonNull
    private final ProfileTypeRepository repository;

    @NonNull
    private final ValidationMessages validationMessages;

    @NonNull
    private final JmsMessageSender jmsMessageSender;

    @Override
    protected CrudRepository<ProfileTypeEntity, Integer> getRepo() {
        return this.repository;
    }

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    public ProfileTypePojo getById(final Integer id) throws ResourceNotFoundException {
        final ProfileTypeEntity entity = this.getEntityById(id);
        final ProfileTypePojo result = ProfileTypePojo.convert(entity);

        return result;
    }

    @Override
    public List<ProfileTypePojo> getAll() {
        final Iterable<ProfileTypeEntity> allEntityes = this.getAllEntityes();
        final List<ProfileTypePojo> result = StreamSupport.stream(allEntityes.spliterator(), false)
            .map(ProfileTypePojo::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ProfileTypePojo deleteById(final Integer id) throws ResourceNotFoundException {
        final ProfileTypeEntity deleted = this.deleteEntityById(id);
        final ProfileTypePojo result = ProfileTypePojo.convert(deleted);

        this.logChanges(deleted, ChangeTypeEnum.DELETE);
        return result;
    }

    @Override
    public ProfileTypePojo save(final ProfileTypePojo typeJson) {
        final ProfileTypeEntity converted = ProfileTypeEntity.convert(typeJson);
        final ProfileTypeEntity saved = this.repository.save(converted);
        final ProfileTypePojo result = ProfileTypePojo.convert(saved);

        this.logChanges(saved, ChangeTypeEnum.CREATE);
        return result;
    }

    @Override
    public ProfileTypePojo update(final ProfileTypePojo cityJson) {
        final Integer id = cityJson.getId();
        final ProfileTypeEntity entity = this.getEntityById(id);

        entity.setName(cityJson.getName());
        final ProfileTypeEntity updated = this.repository.save(entity);
        final ProfileTypePojo result = ProfileTypePojo.convert(updated);

        this.logChanges(updated, ChangeTypeEnum.UPDATE);
        return result;
    }

    private void logChanges(final ProfileTypeEntity entity, final ChangeTypeEnum type) {
        final ChangesOnTableJson changes = new ChangesOnTableJson();
        this.jmsMessageSender.sendToChangeLog(
            changes.fillByEntity(entity),
            ProfileTypeEntity.class,
            entity.getId().longValue(),
            type
        );
    }
}
