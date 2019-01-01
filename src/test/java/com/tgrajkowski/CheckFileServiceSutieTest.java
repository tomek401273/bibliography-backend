package com.tgrajkowski;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.file.CheckFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckFileServiceSutieTest {

    @Autowired
    private CheckFileService checkFileService;

    @Test
    public void getFileExtensionTest1() throws BibliographyException {
        String extension = checkFileService.getFileExtension("test.txt");
        System.out.println(extension);
        Assert.assertEquals("txt", extension);
    }

    @Test
    public void getFileExtensionTestWithoutExtension() {
        String extension = null;
        try {
            extension = checkFileService.getFileExtension("test");
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNull(extension);
    }

    @Test
    public void canFileBeProcessed() {
        String fileExt = "txt";
        boolean isPossible = false;
        try {
             isPossible = checkFileService.canFileBeProcessed(fileExt);
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(isPossible);
    }

    @Test
    public void canFileBeProcessedExtensionNotExist() {
        String fileExt = "";
        boolean isPossible = false;
        try {
            isPossible = checkFileService.canFileBeProcessed(fileExt);
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertFalse(isPossible);
    }

    @Test
    public void canFileBeProcessedWrongExtension() {
        String fileExt = "xyz";
        boolean isPossible = false;
        try {
            isPossible = checkFileService.canFileBeProcessed(fileExt);
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertFalse(isPossible);
    }
}
