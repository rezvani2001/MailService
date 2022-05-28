package com.example.mailService.services;

import com.example.mailService.models.Mail;
import com.example.mailService.models.SendMailWith;
import com.example.mailService.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MailPropertiesService {
    private final MailRepository mailRepository;

    @Autowired
    public MailPropertiesService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public Mail getRecord() {
        List<Mail> mailList = (List<Mail>) mailRepository.findAll();

        if (mailList.size() == 1) {
            return mailList.get(0);
        } else {
            // inserts a default instance of mail properties into DB
            Mail mail = new Mail();
            mail.setId(UUID.randomUUID());
            mail.setServerAddress("smtp.gmail.com");
            mail.setPortNumber("587");

            mail.setProtocol(SendMailWith.TLS);
            mail.setPassword("lywqlydfpkxiiylc");
            mail.setUsername("amirrezvani128@gmail.com");

            mailRepository.save(mail);

            return mail;
        }
    }

    public void updateRecord(Mail mail) {
        Mail original = getRecord();
        mail.setId(original.getId());

        mailRepository.save(mail);
    }
}
