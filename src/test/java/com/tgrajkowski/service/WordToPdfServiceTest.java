package com.tgrajkowski.service;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.model.BibliographyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordToPdfServiceTest {

    @Autowired
    WordToPdfService wordToPdfService;

    @Test
    public void convertDocxToPdf() throws IOException {
        MultipartFile file = MultipartFilesForTests.docx();
        ByteArrayOutputStream byteArrayOutputStream = wordToPdfService.convertDocxToPdf(file);

        OutputStream outputStream = new FileOutputStream("src/test/resources/converted-pdf.pdf");
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.close();
        outputStream.close();
    }
}
