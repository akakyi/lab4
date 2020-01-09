package edu.lab.back.service.jms;

import edu.lab.back.dtoPojos.ChangeLogPojo;

public interface JmsChangeLogMessageHandler {

    String DESTINATION_STR = "change_log";

    String MAIL_DESTINATION_STR = "mail_change";

    void receiveChangeLog(ChangeLogPojo changes);

    void receiveMailChangeLog(final ChangeLogPojo changes);

}
