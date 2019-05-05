package com.tgrajkowski.service;

import com.tgrajkowski.model.mail.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


@Service
public class NewsletterService {

    @Autowired
    private SimpleEmailService simpleEmailService;



    public void sendEmailConfirmSubscription(String email, String username, String confirmCode) {
        String subject = "Computer WebShop Newsletter";
        Mail mail = new Mail(email, subject);
        mail.setWelcome("Welcome in Computer WebShop newsletter");
        mail.setUserName(username);
        mail.setConfirmCode(confirmCode);
        mail.setLinkConfirm("newsletter/confirm?email=" + email + "&code-confirm=" + confirmCode);
        mail.setExplain("You or someone has subscribed to Computer WebShop newsletter using  email: " + mail.getMailTo());
        mail.setMessage("If you want to receive 10% discount in Computer WebShop please confirm this email");
        mail.setTemplate("newsletter");
        mail.setFragment("confirm");
        simpleEmailService.sendMail(mail);
    }



}
