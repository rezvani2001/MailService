package com.example.mailService.models.response;

public class Response {
    private Status status;
    private Object body;

    public Response() {
    }

    public Response(Status status, Object body) {
        this.status = status;
        this.body = body;
    }

    public enum Status {
        FAILED, SUCCESS
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
