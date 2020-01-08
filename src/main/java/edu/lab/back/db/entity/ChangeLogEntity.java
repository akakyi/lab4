package edu.lab.back.db.entity;

import edu.lab.back.dtoPojos.db.json.ChangesOnTable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "change_log")
@Getter
@Setter
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
    private ChangesOnTable changes;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;

}
