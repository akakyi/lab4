package edu.lab.back.dtoPojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ChangeLogEntity;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import lombok.Data;

import java.util.Date;

@Data
public class ChangeLogPojo implements DTOPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "type")
    private ChangeTypePojo changeType;

    @JsonProperty(value = "entity_full_name")
    private String entityFullName;

    @JsonProperty(value = "entity_id")
    private Long entityId;

    @JsonProperty(value = "changes")
    private ChangesOnTableJson changes;

    @JsonProperty(value = "created_on")
    private Date createdOn;

    public static ChangeLogPojo convert(final ChangeLogEntity changeLogEntity) {
        if (changeLogEntity == null) {
            return null;
        }

        final ChangeLogPojo result = new ChangeLogPojo();
        result.setId(changeLogEntity.getId());
        result.setChanges(changeLogEntity.getChanges());
        result.setCreatedOn(changeLogEntity.getCreatedOn());
        result.setEntityFullName(changeLogEntity.getEntityFullName());
        result.setEntityId(changeLogEntity.getEntityId());

        final ChangeTypePojo type = ChangeTypePojo.convert(changeLogEntity.getChangeType());
        result.setChangeType(type);

        return result;
    }

}
