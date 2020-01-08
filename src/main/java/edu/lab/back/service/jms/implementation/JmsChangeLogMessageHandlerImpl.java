package edu.lab.back.service.jms.implementation;

import edu.lab.back.dtoPojos.db.json.ChangesOnTable;
import edu.lab.back.service.jms.JmsChangeLogMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsChangeLogMessageHandlerImpl implements JmsChangeLogMessageHandler {

    private Logger log = LoggerFactory.getLogger(JmsChangeLogMessageHandlerImpl.class);

    @JmsListener(destination = JmsChangeLogMessageHandler.DESTINATION_STR)
    public void receiveChangeLog(ChangesOnTable changes) {
        log.info(changes.toString());
    }

}
