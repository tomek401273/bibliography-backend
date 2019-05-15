package com.tgrajkowski.service;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.service.file.CheckFileService;
import com.tgrajkowski.service.file.read.ReadFactory;
import com.tgrajkowski.service.file.read.ReadFileService;
import com.tgrajkowski.service.file.read.ReadFileServiceTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SortingService {
    @Autowired
    ReadFileServiceTxt readFileServiceTxt;

    @Autowired
    private CheckFileService checkFileService;

    @Autowired
    private ReadFactory readFactory;

    @Autowired
    private DivideFileService divideFileService;

    public ByteArrayOutputStream orderBibliography(MultipartFile multipartFile) throws BibliographyException {
        String extension = checkFileService.getFileExtension(multipartFile.getOriginalFilename());
        checkFileService.canFileBeProcessed(extension);
        ReadFileService readFileService = readFactory.createReadFileService(extension);
        List<String> lines = readFileService.readFile(multipartFile);
        List<String> publicationLines = divideFileService.getBibiographyLines(lines);
        Collections.sort(publicationLines);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);

        for (String s: publicationLines) {
            printWriter.println(s);
        }

        printWriter.close();
        return byteArrayOutputStream;
    }
}
