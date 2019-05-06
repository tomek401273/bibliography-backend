package com.tgrajkowski.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivideFileService {

   public List<String> getDiplomaLines(List<String> allLines) {
        List<String> diplomaLines = new ArrayList<>();

        for (String line: allLines) {
            if (line.contains("Bibiography")) break;
            diplomaLines.add(line);
        }
        return diplomaLines;
    }

    public List<String> getBibiographyLines(List<String> allLines) {
        List<String> bibiographyLines = new ArrayList<>();
        int firstBibiographyLine = 0;
        for (int i =0; i<allLines.size(); i++) {
            String line = allLines.get(i);
            if (line.contains("Bibiography")) {
                firstBibiographyLine = i;
                break;
            }
        }
        firstBibiographyLine++;
        for (int i =firstBibiographyLine; i<allLines.size(); i++) {
            bibiographyLines.add(allLines.get(i));
        }

        return bibiographyLines;
    }

}
