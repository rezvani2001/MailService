package com.example.mailService.models.dto;

import org.springframework.stereotype.Component;

@Component
public class SendMailDTO {
    private String receptor;
    private String message;
    private String subject;

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
