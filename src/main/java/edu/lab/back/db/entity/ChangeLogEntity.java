package edu.lab.back.db.entity;

import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "change_log")
@Getter
@Setter
@ToString
public class ChangeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ch_log_generator")
    @SequenceGenerator(name = "ch_log_generator", sequenceName = "change_log_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ChangeTypeEntity changeType;

    @Column(name = "entity_full_name")
    private String entityFullName;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "changes")
    @Type(type = "ChangesOnTableUserType")
    private ChangesOnTableJson changes;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;

    public static ChangeLogEntity convert(final ChangeLogPojo changeLogPojo) {
        if (changeLogPojo == null) {
            return null;
        }

        final ChangeLogEntity result = new ChangeLogEntity();
        result.setId(changeLogPojo.getId());
        result.setChanges(changeLogPojo.getChanges());
        result.setEntityFullName(changeLogPojo.getEntityFullName());
        result.setEntityId(changeLogPojo.getEntityId());
        result.setCreatedOn(changeLogPojo.getCreatedOn());

        final ChangeTypeEntity type = ChangeTypeEntity.convert(changeLogPojo.getChangeType());
        result.setChangeType(type);

        return result;
    }

}
