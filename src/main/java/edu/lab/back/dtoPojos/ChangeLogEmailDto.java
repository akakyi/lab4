package edu.lab.back.dtoPojos;

import edu.lab.back.db.entity.ChangeLogEmailsEntity;
import lombok.Data;

@Data
public class ChangeLogEmailDto implements DTOPojo {

    private Long id;

    private ChangeTypePojo desiredChangeType;

    private String mail;

    public static ChangeLogEmailDto convert(final ChangeLogEmailsEntity emailsEntity) {
        if (emailsEntity == null) {
            return null;
        }

        final ChangeLogEmailDto changeLogEmailDto = new ChangeLogEmailDto();
        changeLogEmailDto.setId(emailsEntity.getId());
        changeLogEmailDto.setMail(emailsEntity.getMail());

        final ChangeTypePojo type = ChangeTypePojo.convert(emailsEntity.getDesiredChangeType());
        changeLogEmailDto.setDesiredChangeType(type);

        return changeLogEmailDto;
    }

}
