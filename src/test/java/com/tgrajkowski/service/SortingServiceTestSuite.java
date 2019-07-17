package com.tgrajkowski.service;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.SortingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.mock.web.MockMultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SortingServiceTestSuite {
    @Autowired
    private SortingService sortingService;

    @Test
    public void test1() throws BibliographyException {
        // Given
        MultipartFile result = MultipartFilesForTests.txt();
        ByteArrayOutputStream byteArrayOutputStream = null;
        // When
        byteArrayOutputStream = sortingService.orderBibliography(result);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        // Then
        Assert.assertEquals(159, lines.size());
        Assert.assertTrue(lines.get(lines.size() - 3).contains("Zan1, L. M., Facchini, F. D. A., Vasconcelos"));
    }
}
