package com.example.mailService.services;

import com.example.mailService.configurations.MailServiceConfiguration;
import com.example.mailService.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSenderService {
    private final Session session;

    @Autowired
    public MailSenderService(Session session) {
        this.session = session;
    }


    public void sendMail(String subject, String message, String receptor) throws Exception {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("amirrezvani128@gmail.com", "mailService"));
        mimeMessage.addRecipients(Message.RecipientType.TO, receptor);
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
        send(mimeMessage);
    }

    private void send(MimeMessage message) throws Exception {
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new Exception("mail service settings are invalid");
        }

    }

    public void checkNewMailProperties(Mail mail) throws Exception {
        Properties newProperties = MailServiceConfiguration.getDesiredProperties(mail);
        Session newSession = MailServiceConfiguration.getDesiredSession(mail, newProperties);

        MimeMessage mimeMessage = new MimeMessage(newSession);
        mimeMessage.setFrom(new InternetAddress("amirrezvani128@gmail.com", "mailService"));
        mimeMessage.addRecipients(Message.RecipientType.TO, "rezvani2001@gmail.com");
        mimeMessage.setSubject("mailService settings changed");
        mimeMessage.setText(mail.toString());
        send(mimeMessage);

        MailServiceConfiguration.refresh(newProperties, newSession);
    }
}
