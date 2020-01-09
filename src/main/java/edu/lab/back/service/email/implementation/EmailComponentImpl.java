package edu.lab.back.service.email.implementation;

import edu.lab.back.service.email.EmailComponent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailComponentImpl implements EmailComponent {

    @NonNull
    private final JavaMailSender emailSender;

    @Override
    public void sendLogToMail(final String subject, final String text, final String to) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        this.emailSender.send(message);
    }

    @Override
    public void sendLogToMail(final String subject, final String text, final String... to) {
        if (to.length == 0) {
            throw new IllegalArgumentException("Кому отправлять то!?");
        }

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        this.emailSender.send(message);
    }

}
