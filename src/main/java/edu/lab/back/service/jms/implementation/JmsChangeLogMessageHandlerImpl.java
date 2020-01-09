package edu.lab.back.service.jms.implementation;

import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.service.crud.ChangeLogCrudComponent;
import edu.lab.back.service.jms.JmsChangeLogMessageHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsChangeLogMessageHandlerImpl implements JmsChangeLogMessageHandler {

    private Logger log = LoggerFactory.getLogger(JmsChangeLogMessageHandlerImpl.class);

    @NonNull
    private final ChangeLogCrudComponent changeLogCrudComponent;

    //пойдёт по бродкасту, но лан, для лабы сойдёт
    @JmsListener(destination = JmsChangeLogMessageHandler.DESTINATION_STR)
    public void receiveChangeLog(final ChangeLogPojo changes) {
        if (changes == null) {
            return;
        }

        log.info(changes.toString());

        this.changeLogCrudComponent.save(changes);
    }

}
