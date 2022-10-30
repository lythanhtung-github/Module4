package com.codegym.service;

import com.codegym.model.Mail;

import java.util.ArrayList;
import java.util.List;

public class MailService implements IMailService {
    private static final List<Mail> mailList;

    static {
        mailList = new ArrayList<>();
        mailList.add(new Mail(1, "English", 25, false, "12345678"));
        mailList.add(new Mail(2, "Vietnamese", 50, true, "Xin chào!"));
    }

    @Override
    public List<Mail> findAll() {
        return mailList;
    }

    @Override
    public void save(Mail newMail) {
        mailList.add(newMail);
    }

    @Override
    public Mail findById(int id) {
        for (Mail mail : mailList) {
            if(mail.getId()==id){
                return mail;
            }
        }
        return null;
    }

    @Override
    public void update(Mail newMail) {
        for (Mail mail : mailList) {
            if (mail.getId() == newMail.getId()) {
                mail.setLanguage(newMail.getLanguage());
                mail.setPageSize(newMail.getPageSize());
                mail.setSpam(newMail.isSpam());
                mail.setSignature(newMail.getSignature());
            }
        }
    }

    @Override
    public void remove(int id) {
        for (Mail mail : mailList) {
            if (mail.getId() == id) {
                mailList.remove(id);
            }
        }
    }
}
