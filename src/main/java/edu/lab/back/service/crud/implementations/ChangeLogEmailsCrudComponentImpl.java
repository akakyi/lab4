package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ChangeLogEmailsEntity;
import edu.lab.back.db.entity.ChangeTypeEntity;
import edu.lab.back.db.repositories.ChangeLogEmailsRepository;
import edu.lab.back.dtoPojos.ChangeLogEmailDto;
import edu.lab.back.service.crud.ChangeLogEmailsCrudComponent;
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
public class ChangeLogEmailsCrudComponentImpl
    extends BaseCrudService<ChangeLogEmailsEntity, Long>
    implements ChangeLogEmailsCrudComponent
{

    @NonNull
    private final ChangeLogEmailsRepository changeLogEmailsRepository;

    @NonNull
    private final ValidationMessages validationMessages;

    @Override
    protected CrudRepository<ChangeLogEmailsEntity, Long> getRepo() {
        return this.changeLogEmailsRepository;
    }

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    public ChangeLogEmailDto getById(final Long aLong) throws ResourceNotFoundException {
        final ChangeLogEmailsEntity byId = this.getEntityById(aLong);
        final ChangeLogEmailDto result = ChangeLogEmailDto.convert(byId);

        return result;
    }

    @Override
    public List<ChangeLogEmailDto> getAll() {
        final Iterable<ChangeLogEmailsEntity> allEntityes = this.getAllEntityes();

        final List<ChangeLogEmailDto> result = StreamSupport.stream(allEntityes.spliterator(), false)
            .map(ChangeLogEmailDto::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ChangeLogEmailDto deleteById(final Long aLong) throws ResourceNotFoundException {
        final ChangeLogEmailsEntity deleted = this.deleteEntityById(aLong);

        final ChangeLogEmailDto result = ChangeLogEmailDto.convert(deleted);
        return result;
    }

    @Override
    public ChangeLogEmailDto save(final ChangeLogEmailDto json) {
        final ChangeLogEmailsEntity converted = ChangeLogEmailsEntity.convert(json);
        this.changeLogEmailsRepository.save(converted);

        return null;
    }

    @Override
    public ChangeLogEmailDto update(final ChangeLogEmailDto json) {
        final Long id = json.getId();
        final ChangeLogEmailsEntity entity = this.getEntityById(id);

        entity.setId(json.getId());
        entity.setMail(json.getMail());

        final ChangeTypeEntity type = ChangeTypeEntity.convert(json.getDesiredChangeType());
        entity.setDesiredChangeType(type);

        final ChangeLogEmailsEntity saved = this.changeLogEmailsRepository.save(entity);
        final ChangeLogEmailDto result = ChangeLogEmailDto.convert(saved);

        return result;
    }
}
