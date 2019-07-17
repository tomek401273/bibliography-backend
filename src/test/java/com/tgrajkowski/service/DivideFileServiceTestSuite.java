package com.tgrajkowski.service;

import com.tgrajkowski.service.DivideFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DivideFileServiceTestSuite {
    @Autowired
    private DivideFileService divideFileService;

    @Test
    public void getDiplomaLines(){
        List<String> lines = new ArrayList<>();
        lines.add("line1");
        lines.add("line2");
        lines.add("Bibiography");
        lines.add("line4");

        List<String> result = divideFileService.getDiplomaLines(lines);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("line1", result.get(0));
        Assert.assertEquals("line2", result.get(1));
    }

    @Test
    public void getBibiographyLines() {
        List<String> lines = new ArrayList<>();
        lines.add("line1");
        lines.add("line2");
        lines.add("Bibiography");
        lines.add("line4");
        lines.add("line5");

        List<String> result = divideFileService.getBibiographyLines(lines);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("line4", result.get(0));
        Assert.assertEquals("line5", result.get(1));
    }
}
