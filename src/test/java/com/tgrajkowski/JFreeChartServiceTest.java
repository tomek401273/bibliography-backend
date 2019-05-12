package com.tgrajkowski;

import com.tgrajkowski.service.JFreeChartService;
import com.tgrajkowski.service.JFreeChartServiceTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JFreeChartServiceTest {
    @Autowired
    private JFreeChartService jFreeChartService;

    @Autowired
    private JFreeChartServiceTime jFreeChartServiceTime;

    @Test
    public void test1() {
//        jFreeChartService.createChart2();
        jFreeChartServiceTime.create();
    }

}
