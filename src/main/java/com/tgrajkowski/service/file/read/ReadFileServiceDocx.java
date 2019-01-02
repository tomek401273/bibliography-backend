package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadFileServiceDocx implements ReadFileService{
    @Override
    public List<String> readFile(MultipartFile multipartFile) throws BibliographyException {
        try (InputStream inputStream = multipartFile.getInputStream()){
            XWPFDocument document = new XWPFDocument(inputStream);
            return document.getParagraphs().stream().map(x->x.getText()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new BibliographyException(e.getMessage());
        }
    }
}
