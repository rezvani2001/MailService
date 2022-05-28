package com.example.mailService.repositories;

import com.example.mailService.models.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailRepository extends CrudRepository<Mail, UUID> {
}
