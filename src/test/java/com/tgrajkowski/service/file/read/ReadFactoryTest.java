package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadFactoryTest {
    @Autowired
    private ReadFactory readFactory;

    @Test
    public void createReadFileServiceTxt() throws BibliographyException {
        ReadFileService readFileService = null;
        readFileService = readFactory.createReadFileService("txt");

        Assert.assertEquals(new ReadFileServiceTxt().getClass(), readFileService.getClass());
    }

    @Test
    public void createReadFileServiceDoc() throws BibliographyException {
        ReadFileService readFileService = null;
        readFileService = readFactory.createReadFileService("doc");
        Assert.assertEquals(new ReadFileServiceDoc().getClass(), readFileService.getClass());
    }

    @Test
    public void createReadFileServiceDocx() throws BibliographyException {
        ReadFileService readFileService =  readFactory.createReadFileService("docx");
        Assert.assertEquals(new ReadFileServiceDocx().getClass(), readFileService.getClass());
    }
}
