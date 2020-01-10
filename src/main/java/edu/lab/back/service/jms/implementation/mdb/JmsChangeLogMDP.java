package edu.lab.back.service.jms.implementation.mdb;

import edu.lab.back.dtoPojos.ChangeLogEmailDto;
import edu.lab.back.dtoPojos.ChangeLogPojo;
import edu.lab.back.service.crud.ChangeLogCrudComponent;
import edu.lab.back.service.crud.ChangeLogEmailsCrudComponent;
import edu.lab.back.service.email.EmailComponent;
import edu.lab.back.service.jms.JmsChangeLogMessageHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JmsChangeLogMDP implements JmsChangeLogMessageHandler {

    private Logger log = LoggerFactory.getLogger(JmsChangeLogMDP.class);

    private final String MESSAGE_SUBJECT = "Changes!!!";

    @NonNull
    private final ChangeLogCrudComponent changeLogCrudComponent;

    @NonNull
    private final ChangeLogEmailsCrudComponent changeLogEmailsCrudComponent;

    @NonNull
    private final EmailComponent emailComponent;

    //пойдёт по бродкасту, но лан, для лабы сойдёт
    @JmsListener(destination = JmsChangeLogMessageHandler.DESTINATION_STR)
    public void receiveChangeLog(final ChangeLogPojo changes) {
        if (changes == null) {
            return;
        }

        log.info(changes.toString());

        this.changeLogCrudComponent.save(changes);
    }

    @JmsListener(destination = JmsChangeLogMessageHandler.DESTINATION_STR)
    public void receiveMailChangeLog(final ChangeLogPojo changes) {
        final List<ChangeLogEmailDto> mails = this.changeLogEmailsCrudComponent.getAll();

        //TODO как будет не лень - перенести это в sql в репо
        final String[] to = mails.stream()
            .filter(m -> m.getDesiredChangeType().equals(changes.getChangeType()))
            .map(ChangeLogEmailDto::getMail)
            .toArray(String[]::new);

        if (to.length != 0) {
            this.emailComponent.sendLogToMail(MESSAGE_SUBJECT, changes.toString(), to);
        }
    }

}
