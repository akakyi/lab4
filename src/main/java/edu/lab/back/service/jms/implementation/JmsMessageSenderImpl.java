package edu.lab.back.service.jms.implementation;

import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.dtoPojos.ChangeTypePojo;
import edu.lab.back.dtoPojos.db.json.ChangesOnTableJson;
import edu.lab.back.service.jms.JmsChangeLogMessageHandler;
import edu.lab.back.service.jms.JmsMessageSender;
import edu.lab.back.util.ChangeTypeEnum;
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
    public ChangeLogPojo sendToChangeLog(
        final ChangesOnTableJson changes,
        @NonNull Class entityClass,
        @NonNull Long entityId,
        @NonNull ChangeTypeEnum typeEnum
    )
    {
        log.info("send");
        if (changes == null) {
            return null;
        }
        log.info(JmsChangeLogMessageHandler.DESTINATION_STR);

        final ChangeLogPojo payload = new ChangeLogPojo();
        payload.setEntityId(entityId);
        payload.setEntityFullName(entityClass.getCanonicalName());
        payload.setChanges(changes);

        final ChangeTypePojo type = ChangeTypePojo.getByEnum(typeEnum);
        payload.setChangeType(type);

        this.jmsTemplate.convertAndSend(JmsChangeLogMessageHandler.DESTINATION_STR, payload);
        this.jmsTemplate.convertAndSend(JmsChangeLogMessageHandler.MAIL_DESTINATION_STR, payload);

        return payload;
    }

}
