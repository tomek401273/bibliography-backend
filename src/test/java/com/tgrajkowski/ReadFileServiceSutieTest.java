package com.tgrajkowski;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.ReadFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadFileServiceSutieTest {

    @Autowired
    private ReadFileService readFileService;

    @Test
    public void getFileExtensionTest1() throws BibliographyException {
        String extension = readFileService.getFileExtension("test.txt");
        System.out.println(extension);
        Assert.assertEquals("txt", extension);
    }

    @Test
    public void getFileExtensionTestWithoutExtension() {
        String extension = null;
        try {
            extension = readFileService.getFileExtension("test");
        } catch (BibliographyException e) {
            e.printStackTrace();
        }
        Assert.assertNull(extension);
    }


}
