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
    public void createReadFileServiceTxt() {
        ReadFileService readFileService = null;

        try {
           readFileService= readFactory.createReadFileService("txt");
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(new ReadFileServiceTxt().getClass(), readFileService.getClass());
    }

    @Test
    public void createReadFileServiceDoc() {
        ReadFileService readFileService = null;

        try {
           readFileService= readFactory.createReadFileService("doc");
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(new ReadFileServiceDoc().getClass(), readFileService.getClass());
    }

    @Test
    public void createReadFileServiceDocx() {
        ReadFileService readFileService = null;

        try {
            readFileService= readFactory.createReadFileService("docx");
        } catch (BibliographyException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(new ReadFileServiceDocx().getClass(), readFileService.getClass());
    }
}
