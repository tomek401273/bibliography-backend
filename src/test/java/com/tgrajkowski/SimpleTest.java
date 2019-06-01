package com.tgrajkowski;
import java.sql.Timestamp;

import org.junit.Test;

import java.util.Date;

public class SimpleTest {

    @Test
    public void test1() {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        System.out.println(date.getTime());
    }
}
