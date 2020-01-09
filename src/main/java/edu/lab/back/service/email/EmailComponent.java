package edu.lab.back.service.email;

public interface EmailComponent {

    void sendLogToMail(String subject, String text, String to);

    void sendLogToMail(String subject, String text, String... to);

}
