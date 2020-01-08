package edu.lab.back.service.jms.implementation;

import edu.lab.back.dtoPojos.db.json.ChangesOnTable;
import edu.lab.back.service.jms.JmsChangeLogMessageHandler;
import edu.lab.back.service.jms.JmsMessageSender;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsMessageSenderImpl implements JmsMessageSender {

    @NonNull
    private JmsTemplate jmsTemplate;

    private Logger log = LoggerFactory.getLogger(JmsMessageSenderImpl.class);

    @Override
    public ChangesOnTable sendToChangeLog(final ChangesOnTable changes) {
        log.info("send");

        if (changes != null) {
            log.info(JmsChangeLogMessageHandler.DESTINATION_STR);
            this.jmsTemplate.convertAndSend(JmsChangeLogMessageHandler.DESTINATION_STR, changes);
        }

        return changes;
    }
}
