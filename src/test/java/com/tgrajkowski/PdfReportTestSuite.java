package com.tgrajkowski;

import com.tgrajkowski.service.ODT2PDF.DocConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PdfReportTestSuite {
    @Test
    public void test1() {
        System.out.println("Logic logic logic");
        DocConverter docConverter = new DocConverter();
        docConverter.konwertuj();
    }
}
