package com.tgrajkowski;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.file.CheckFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckFileServiceTestSuite {
    @Autowired
    private CheckFileService checkFileService;

    @Test
    public void readFile() {
        Path path = Paths.get("/home/tomek/Documents/samples2/bibliography-backend/all.txt");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);
        List<String> lines = new ArrayList<>();
        try {
           lines = checkFileService.readFile(result);
        } catch (BibliographyException e) {}

        Assert.assertEquals(315, lines.size());
        Assert.assertEquals("I.\tWstęp", lines.get(0));
    }

    @Test
    public void getFileExtensionOk() {
        String fileName = "bibliography.txt";
        String extension="";
        try {
            extension =checkFileService.getFileExtension(fileName);
        } catch (BibliographyException e) {}
        Assert.assertEquals("txt", extension);
    }

    @Test
    public void getFileExtensionNotOk() {
        String fileName = "bibliography";
        String extension="";
        try {
            extension =checkFileService.getFileExtension(fileName);
        } catch (BibliographyException e) {}
        Assert.assertEquals("", extension);
    }

    @Test
    public void canFileBeProcessedOk() {
        boolean isOk = false;
        try {
            isOk = checkFileService.canFileBeProcessed("txt");
        } catch (BibliographyException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, isOk);
    }

    @Test
    public void canFileBeProcessedNotOk() {
        boolean isOk = false;
        try {
            isOk = checkFileService.canFileBeProcessed("jpg");
        } catch (BibliographyException e) {}
        Assert.assertEquals(false, isOk);
    }
}
