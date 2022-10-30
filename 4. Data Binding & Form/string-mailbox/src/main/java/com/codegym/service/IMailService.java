package com.codegym.service;

import com.codegym.model.Mail;

import java.util.List;

public interface IMailService {
    List<Mail> findAll();

    void save(Mail mail);

    Mail findById(int id);

    void update(Mail mail);

    void remove(int id);
}
