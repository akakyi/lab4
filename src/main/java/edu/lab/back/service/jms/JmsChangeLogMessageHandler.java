package edu.lab.back.service.jms;

import edu.lab.back.dtoPojos.db.json.ChangesOnTable;

public interface JmsChangeLogMessageHandler {

    String DESTINATION_STR = "change_log";

    void receiveChangeLog(ChangesOnTable changes);

}
