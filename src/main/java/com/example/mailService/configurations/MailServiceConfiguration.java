package com.example.mailService.configurations;

import com.example.mailService.models.Mail;
import com.example.mailService.models.SendMailWith;
import com.example.mailService.services.MailPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailServiceConfiguration {
    private static MailPropertiesService mailPropertiesService;
    private static Properties properties;
    private static Session session;


    @Autowired
    public MailServiceConfiguration(MailPropertiesService mailPropertiesService) {
        MailServiceConfiguration.mailPropertiesService = mailPropertiesService;
    }

    @Bean(name = "mailProperties")
    public Properties mailProperties() {
        if (properties == null) {
            Mail record = mailPropertiesService.getRecord();

            properties = getDesiredProperties(record);
        }

        return properties;
    }

    public static Properties getDesiredProperties(Mail record) {
        properties = new Properties();

        properties.setProperty("mail.smtp.host", record.getServerAddress());
        properties.setProperty("mail.smtp.port", record.getPortNumber());

        if (record.getProtocol().equals(SendMailWith.SSL))
            properties.setProperty("mail.smtp.ssl.enable", "true");
        else properties.setProperty("mail.smtp.ssl.enable", "false");

        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");


        return properties;
    }

    @Bean(name = "mailSession")
    public Session sessionBean(@Autowired @Qualifier("mailProperties") Properties properties) {
        if (session == null) {
            Mail record = mailPropertiesService.getRecord();

            session = getDesiredSession(record, properties);
        }

        return session;
    }

    public static Session getDesiredSession(Mail record, Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(record.getUsername(), record.getPassword());
            }
        });
    }

    public static void refresh(Properties properties,Session session) {
        MailServiceConfiguration.setProperties(properties);
        MailServiceConfiguration.session = session;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        MailServiceConfiguration.properties = properties;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        MailServiceConfiguration.session = session;
    }
}
