package com.tgrajkowski.service;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.model.file.formats.FileFormats;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ReadFileService {
    public void readFile(MultipartFile multipartFile) throws IOException, BibliographyException {
        String temp = "temp/"+multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()){
            Files.copy(inputStream, Paths.get(temp), StandardCopyOption.REPLACE_EXISTING);
            String fileExtension = getFileExtension(multipartFile.getOriginalFilename());

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public String getFileExtension(String fullFileName) throws BibliographyException{
        int lastDot = fullFileName.lastIndexOf(".");
        if (lastDot == -1) {
            throw new BibliographyException("File without extension");
        }
        return fullFileName.substring(lastDot+1);
    }

    public void canFileBeProcessed(String extension) throws BibliographyException {
        try {
            FileFormats.valueOf(extension);
        } catch (IllegalArgumentException e) {
            throw new BibliographyException("Files with this extension: "+extension+" can't be processed");
        }
    }
}
