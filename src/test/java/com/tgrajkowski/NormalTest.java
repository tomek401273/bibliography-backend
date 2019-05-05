package com.tgrajkowski;

import org.junit.Test;

public class NormalTest {
    @Test
    public void test1() {
        String email ="tomek371240@gmail.com";
        String name = email.substring(0, email.lastIndexOf("@"));
        System.out.println(name);
    }
}
