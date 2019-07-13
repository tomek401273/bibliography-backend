package com.tgrajkowski;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.SortingService;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.mock.web.MockMultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortingServiceTestSuite {
    @Autowired
    private SortingService sortingService;

    @Test
    public void test1() {
        System.out.println("Logic");
//        MultipartFile multipartFile
//        sortingService.sort();

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
        try {
            ByteArrayOutputStream byteArrayOutputStream = sortingService.orderBibliography(result);
            try (OutputStream outputStream = new FileOutputStream("all-in-order.txt")) {
                byteArrayOutputStream.writeTo(outputStream);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (BibliographyException e) {
            e.printStackTrace();
        }

    }
}
