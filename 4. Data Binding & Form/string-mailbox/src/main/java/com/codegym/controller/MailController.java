package com.codegym.controller;

import com.codegym.model.Mail;
import com.codegym.service.IMailService;
import com.codegym.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("mail")
public class MailController {
    private final IMailService mailService = new MailService();
    private final String[] languages = {"English", "Vietnamese", "Chinese","Japanese"};
    private final int[] pages = {5, 10, 15, 25, 50, 100};

    @GetMapping
    public String showList(ModelMap model) {
        List<Mail> mailList = mailService.findAll();
        model.addAttribute("mailList", mailList);
        return "/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("mail", mailService.findById(id));
        model.addAttribute("languages", languages);
        model.addAttribute("pages", pages);
        return "/edit";
    }

    @PostMapping("/update")
    public String update(Mail mail) {
        mailService.update(mail);
        return "redirect:/mail";
    }
}