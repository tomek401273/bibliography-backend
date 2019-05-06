package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadFileServiceTxt implements ReadFileService {
    @Override
    public List<String> readFile(MultipartFile multipartFile) throws BibliographyException {
        System.out.println("textfile");

        try (InputStream inputStream = multipartFile.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new BibliographyException("Ocuured problem with reading file");
        }
    }

    public List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());
        Path path = Paths.get(file.getPath());
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Something go wrong with file.....");
            System.out.println(e);
        }
        return lines;
    }
}
