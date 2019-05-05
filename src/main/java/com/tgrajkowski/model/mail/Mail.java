package com.tgrajkowski.model.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String userName;
    private String confirmCode;
    private MailType mailType;
    private String explain;
    private String welcome;
    private String linkConfirm;
    private boolean confirmAccount = false;
    private String template;
    private String fragment;

    public Mail(String mailTo, String subject, String message, MailType mailType) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.mailType = mailType;
    }

    public Mail(String mailTo, String subject, MailType mailType) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.mailType = mailType;
    }

    public Mail(String mailTo, String subject) {
        this.mailTo = mailTo;
        this.subject = subject;
    }
}
