package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ChangeLogEntity;
import edu.lab.back.db.entity.ChangeTypeEntity;
import edu.lab.back.db.repositories.ChangeLogRepository;
import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.service.crud.ChangeLogCrudComponent;
import edu.lab.back.util.constants.ValidationMessages;
import edu.lab.back.util.exception.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ChangeLogCrudComponentImpl
    extends BaseCrudService<ChangeLogEntity, Long>
    implements ChangeLogCrudComponent
{

    @NonNull
    private final ChangeLogRepository changeLogRepository;

    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    protected CrudRepository<ChangeLogEntity, Long> getRepo() {
        return this.changeLogRepository;
    }

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    public ChangeLogPojo getById(final Long aLong) throws ResourceNotFoundException {
        final ChangeLogEntity byId = this.getEntityById(aLong);
        final ChangeLogPojo result = ChangeLogPojo.convert(byId);

        return result;
    }

    @Override
    public List<ChangeLogPojo> getAll() {
        final Iterable<ChangeLogEntity> allEntityes = this.getAllEntityes();

        final List<ChangeLogPojo> result = StreamSupport.stream(allEntityes.spliterator(), false)
            .map(ChangeLogPojo::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ChangeLogPojo deleteById(final Long aLong) throws ResourceNotFoundException {
        final ChangeLogEntity deleted = this.deleteEntityById(aLong);

        final ChangeLogPojo result = ChangeLogPojo.convert(deleted);
        return result;
    }

    @Override
    public ChangeLogPojo save(final ChangeLogPojo json) {
        final ChangeLogEntity converted = ChangeLogEntity.convert(json);
        this.changeLogRepository.save(converted);

        return null;
    }

    @Override
    public ChangeLogPojo update(final ChangeLogPojo json) {
        final Long id = json.getId();
        final ChangeLogEntity entity = this.getEntityById(id);

        entity.setId(json.getId());
        entity.setEntityId(json.getEntityId());
        entity.setEntityFullName(json.getEntityFullName());
        entity.setChanges(json.getChanges());

        final ChangeTypeEntity type = ChangeTypeEntity.convert(json.getChangeType());
        entity.setChangeType(type);

        final ChangeLogEntity saved = this.changeLogRepository.save(entity);
        final ChangeLogPojo result = ChangeLogPojo.convert(saved);

        return result;
    }

}
