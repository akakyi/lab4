package edu.lab.back.db.entity;

import edu.lab.back.dtoPojos.ChangeLogEmailDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "change_log_emails")
@Getter
@Setter
@ToString
public class ChangeLogEmailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ch_log_email_generator")
    @SequenceGenerator(
        name = "ch_log_email_generator",
        sequenceName = "change_log_emails_id_sequence",
        allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "desired_type_id")
    private ChangeTypeEntity desiredChangeType;

    @Column(name = "mail")
    private String mail;

    public static ChangeLogEmailsEntity convert(final ChangeLogEmailDto changeLogEmailDto) {
        if (changeLogEmailDto == null) {
            return null;
        }

        final ChangeLogEmailsEntity result = new ChangeLogEmailsEntity();
        result.setId(changeLogEmailDto.getId());
        result.setMail(changeLogEmailDto.getMail());

        final ChangeTypeEntity type = ChangeTypeEntity.convert(changeLogEmailDto.getDesiredChangeType());
        result.setDesiredChangeType(type);

        return result;
    }

}
