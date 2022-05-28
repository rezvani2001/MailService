package com.example.mailService.controllers;

import com.example.mailService.models.dto.SendMailDTO;
import com.example.mailService.models.response.Response;
import com.example.mailService.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSenderController {
    private final MailSenderService senderService;

    @Autowired
    public MailSenderController(MailSenderService senderService) {
        this.senderService = senderService;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/send")
    public ResponseEntity<Object> sendMail (@RequestBody SendMailDTO mailDTO) throws Exception {
        senderService.sendMail(mailDTO.getSubject(), mailDTO.getMessage(), mailDTO.getReceptor());

        return ResponseEntity.ok(new Response(Response.Status.SUCCESS, "email has been sent to " +
                mailDTO.getReceptor() + " successfully"));
    }
}
