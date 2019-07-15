package com.tgrajkowski;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultipartFilesForTests {
    public static MultipartFile txt () {
        byte[] content = null;
        try {content = Files.readAllBytes(Paths.get("src/test/resources/txt.txt")); } catch (final IOException e) { }
        MultipartFile multipartFile = new MockMultipartFile("file.txt", "file.txt",
                "text/plain",
                content);
        return multipartFile;
    }

    public static MultipartFile doc () {
        byte[] content = null;
        try {content = Files.readAllBytes(Paths.get("src/test/resources/doc.doc")); } catch (final IOException e) { }
        MultipartFile multipartFile = new MockMultipartFile("doc.doc", "doc.doc",
                "application/msword",
                content);
        return multipartFile;
    }   public static MultipartFile docx () {
        byte[] content = null;
        try {content = Files.readAllBytes(Paths.get("src/test/resources/docx.docx")); } catch (final IOException e) { }
        MultipartFile multipartFile = new MockMultipartFile("docx.docx", "docx.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                content);
        return multipartFile;
    }
}
