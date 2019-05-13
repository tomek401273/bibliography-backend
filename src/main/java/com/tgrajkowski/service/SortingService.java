package com.tgrajkowski.service;

import com.tgrajkowski.service.file.read.ReadFileServiceTxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SortingService {
    @Autowired
    ReadFileServiceTxt readFileServiceTxt;
    public void sort() {
        long start = System.currentTimeMillis();
//        FileReader fileReader = new FileReader();
//        List<String> publicationLines = fileReader.readFile("file/text.txt");
        List<String> publicationLines = readFileServiceTxt.readFile("file/text.txt");
        for (String pub: publicationLines){
            System.out.println(pub);
        }
        Collections.sort(publicationLines);
        for (String pub: publicationLines){
            pub= "\t"+pub;
            System.out.println(pub);
        }
    }
}
