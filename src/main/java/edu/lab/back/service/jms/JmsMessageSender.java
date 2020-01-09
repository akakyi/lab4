package edu.lab.back.service.jms;

import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import edu.lab.back.util.ChangeTypeEnum;

public interface JmsMessageSender {

    ChangeLogPojo sendToChangeLog(ChangesOnTableJson changes, Class entityClass, Long entityId, ChangeTypeEnum type);

}
