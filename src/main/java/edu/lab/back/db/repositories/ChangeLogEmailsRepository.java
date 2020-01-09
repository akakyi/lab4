package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ChangeLogEmailsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChangeLogEmailsRepository extends CrudRepository<ChangeLogEmailsEntity, Long> {
}
