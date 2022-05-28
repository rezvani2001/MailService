package com.example.mailService.models;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Mail {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 10, message = "server address must be at least 10 charactos")
    private String serverAddress;

    @Min(value = 0, message = "port number cannot be negetive")
    private String portNumber;

    @Size(min = 10, message = "username minimum length must be 9")
    private String username;

    private String password;

    private SendMailWith protocol;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public SendMailWith getProtocol() {
        return protocol;
    }

    public void setProtocol(SendMailWith protocol) {
        this.protocol = protocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t \"id\" : " + id + '\"' +
                ",\n\t \"serverAddress\" : \"" + serverAddress + '\"' +
                ",\n\t \"portNumber\" : \"" + portNumber + '\"' +
                ",\n\t \"username : \"" + username + '\"' +
                ",\n\t \"password : \"" + password + '\"' +
                ",\n\t \"protocol : \"" + protocol.toString() + "\"\n" +
                '}';
    }
}
