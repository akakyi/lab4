package edu.lab.back.service.jms;

import edu.lab.back.dtoPojos.db.json.ChangesOnTable;

public interface JmsMessageSender {

    ChangesOnTable sendToChangeLog(ChangesOnTable changes);
}
