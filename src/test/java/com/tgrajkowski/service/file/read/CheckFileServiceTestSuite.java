package com.tgrajkowski.service.file.read;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.file.CheckFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CheckFileServiceTestSuite {
    @Autowired
    private CheckFileService checkFileService;

    @Test
    public void readFile() throws BibliographyException {
        MultipartFile result = MultipartFilesForTests.txt();
        List<String> lines = checkFileService.readFile(result);
        Assert.assertEquals(314, lines.size());
        Assert.assertEquals("I.\tWstęp", lines.get(0));
    }

    @Test
    public void getFileExtensionOk() {
        String fileName = "bibliography.txt";
        String extension = "";
        try {
            extension = checkFileService.getFileExtension(fileName);
        } catch (BibliographyException e) {
        }
        Assert.assertEquals("txt", extension);
    }

    @Test
    public void getFileExtensionNotOk() {
        String fileName = "bibliography";
        String extension = "";
        try {
            extension = checkFileService.getFileExtension(fileName);
        } catch (BibliographyException e) {
        }
        Assert.assertEquals("", extension);
    }

    @Test
    public void canFileBeProcessedOk() throws BibliographyException {
        boolean isOk = checkFileService.canFileBeProcessed("txt");
        Assert.assertEquals(true, isOk);
    }

    @Test
    public void canFileBeProcessedNotOk() {
        boolean isOk = false;
        try {
            isOk = checkFileService.canFileBeProcessed("jpg");
        } catch (BibliographyException e) {
        }
        Assert.assertEquals(false, isOk);
    }
}
