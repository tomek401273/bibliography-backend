package com.tgrajkowski;

import com.tgrajkowski.service.main.NewsletterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEmailSuite {
    @Autowired
    public NewsletterService newsletterService;
    @Test
    public void test1() {
//        newsletterService.sendEmailConfirmSubscription("tomek371240@gmail.com", "tomek", "logiclogic");
    }
}
