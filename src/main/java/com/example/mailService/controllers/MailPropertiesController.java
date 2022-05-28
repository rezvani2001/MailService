package com.example.mailService.controllers;

import com.example.mailService.models.Mail;
import com.example.mailService.models.response.Response;
import com.example.mailService.services.MailPropertiesService;
import com.example.mailService.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MailPropertiesController {
    MailPropertiesService propertiesService;
    MailSenderService senderService;

    @Autowired
    public MailPropertiesController(MailPropertiesService propertiesService, MailSenderService senderService) {
        this.propertiesService = propertiesService;
        this.senderService = senderService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> updateRecord(@RequestBody @Valid Mail mail) throws Exception {
        if (mail.getServerAddress().contains("gmail")) {
            senderService.checkNewMailProperties(mail);

            propertiesService.updateRecord(mail);
        } else
            return ResponseEntity.status(400).body(new Response(Response.Status.FAILED, "this server only accepts gmail"));

        return ResponseEntity.ok(new Response(Response.Status.SUCCESS, "mail properties cahnged successfully"));
    }

    @RequestMapping
    public ResponseEntity<Object> getRecord() {
        return ResponseEntity.ok(new Response(Response.Status.SUCCESS, propertiesService.getRecord()));
    }
}
