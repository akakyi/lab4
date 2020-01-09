package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.db.repositories.ProfileRepository;
import edu.lab.back.db.repositories.ProfileTypeRepository;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import edu.lab.back.dtoPojos.request.ProfileRequestPojo;
import edu.lab.back.dtoPojos.response.ProfileResponsePojo;
import edu.lab.back.service.crud.ProfileService;
import edu.lab.back.service.jms.JmsMessageSender;
import edu.lab.back.util.ChangeTypeEnum;
import edu.lab.back.util.constants.ValidationMessages;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProfileServiceImpl extends BaseCrudService<ProfileEntity, Long> implements ProfileService {

    @NonNull
    private final ProfileRepository profileRepository;

    @NonNull
    private final ProfileTypeRepository profileTypeRepository;

    @NonNull
    private final ValidationMessages validationMessages;

    @NonNull
    private final JmsMessageSender jmsMessageSender;

    @Override
    protected ValidationMessages getValidationMessages() {
        return this.validationMessages;
    }

    @Override
    protected ProfileRepository getRepo() {
        return this.profileRepository;
    }

    @Override
    public ProfileResponsePojo getById(@NonNull final Long id) {
        final ProfileEntity profile = this.getEntityById(id);

        final ProfileResponsePojo converted = ProfileResponsePojo.convert(profile);
        return converted;
    }

    @Override
    public List<ProfileResponsePojo> getAll() {
        final Iterable<ProfileEntity> allProfiles = this.getAllEntityes();
        final List<ProfileResponsePojo> result = StreamSupport.stream(allProfiles.spliterator(), false)
            .map(ProfileResponsePojo::convert)
            .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return result;
    }

    @Override
    public ProfileResponsePojo deleteById(@NonNull final Long id) {
        final ProfileEntity deletedEntity = this.deleteEntityById(id);

        final ProfileResponsePojo result = ProfileResponsePojo.convert(deletedEntity);
        this.logChanges(deletedEntity, ChangeTypeEnum.DELETE);
        return result;
    }

    @Override
    public ProfileResponsePojo save(@NonNull final ProfileRequestPojo profileJson) {
        final ProfileEntity entity = new ProfileEntity();
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileRepository.save(entity);
        final ProfileResponsePojo savedJson = ProfileResponsePojo.convert(saved);
        this.logChanges(saved, ChangeTypeEnum.CREATE);
        return savedJson;
    }

    @Override
    public ProfileResponsePojo update(@NonNull final ProfileRequestPojo profileJson) {
        final ProfileEntity entity = this.getEntityById(profileJson.getId());
        this.fillEntity(entity, profileJson);

        final ProfileEntity saved = this.profileRepository.save(entity);
        final ProfileResponsePojo savedJson = ProfileResponsePojo.convert(saved);
        this.logChanges(saved, ChangeTypeEnum.UPDATE);
        return savedJson;
    }

    @Override
    public List<ProfileResponsePojo> getProfileBySchoolId(final Long id) {
        final List<ProfileEntity> profilesBySchoolEntities = this.profileRepository.getProfilesBySchoolId(id);

        final List<ProfileResponsePojo> result = profilesBySchoolEntities.stream()
            .map(ProfileResponsePojo::convert)
            .collect(Collectors.toList());

        return result;
    }

    private void fillEntity(@NonNull final ProfileEntity entity, @NonNull final ProfileRequestPojo profileJson) {
        entity.setName(profileJson.getName());
        entity.setClassLevel(profileJson.getClassLevel());
        entity.setAge(profileJson.getAge());

        final SchoolEntity school = new SchoolEntity();
        school.setId(profileJson.getSchoolId());
        entity.setSchool(school);

        final String profileTypeName = profileJson.getProfileType().getName();
        final ProfileTypeEntity profileType = this.profileTypeRepository.getByName(profileTypeName);
        entity.setProfileType(profileType);
    }

    private void logChanges(final ProfileEntity entity, final ChangeTypeEnum type) {
        final ChangesOnTableJson changes = new ChangesOnTableJson();
        this.jmsMessageSender.sendToChangeLog(
            changes.fillByEntity(entity),
            ProfileEntity.class,
            entity.getId(),
            type
        );
    }
}
