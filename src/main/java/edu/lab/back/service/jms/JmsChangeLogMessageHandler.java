package edu.lab.back.service.jms;

import edu.lab.back.dtoPojos.ChangeLogPojo;

public interface JmsChangeLogMessageHandler {

    String DESTINATION_STR = "change_log";

    void receiveChangeLog(ChangeLogPojo changes);

}
