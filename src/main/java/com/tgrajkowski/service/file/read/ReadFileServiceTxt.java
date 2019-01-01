package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadFileServiceTxt implements ReadFileService {
    @Override
    public List<String> readFile(MultipartFile multipartFile) throws BibliographyException {
        try (InputStream inputStream = multipartFile.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new BibliographyException("Ocuured problem with reading file");
        }
    }
}
