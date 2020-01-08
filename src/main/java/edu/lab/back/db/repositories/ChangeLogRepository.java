package edu.lab.back.db.repositories;

import edu.lab.back.db.entity.ChangeLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends CrudRepository<ChangeLogEntity, Long> {
}
