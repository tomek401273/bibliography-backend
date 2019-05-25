package com.tgrajkowski.model.newsletter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class Subscriber {
    Long id;

    private String name;

    private String email;

    private String code;

    private Date lastNewsletterSend;

    private boolean isConfirm;

    private String confirmCode;

    public Subscriber(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
