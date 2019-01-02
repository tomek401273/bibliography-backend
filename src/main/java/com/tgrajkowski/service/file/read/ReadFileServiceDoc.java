package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class ReadFileServiceDoc implements ReadFileService {
    @Override
    public List<String> readFile(MultipartFile multipartFile) throws BibliographyException {
        WordExtractor extractor = null;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            HWPFDocument document = new HWPFDocument(inputStream);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            return Arrays.asList(fileData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new BibliographyException(e.getMessage());
        }
    }
}
