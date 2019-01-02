package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReadFactory {
    private Map<String, ReadFileService> avaiableReader;

    public ReadFactory() {
        this.avaiableReader = new HashMap<>();
        avaiableReader.put("txt", new ReadFileServiceTxt());
        avaiableReader.put("doc", new ReadFileServiceDoc());
        avaiableReader.put("docx", new ReadFileServiceDocx());
    }

    public ReadFileService createReadFileService(String extension) throws BibliographyException{
        if (avaiableReader.containsKey(extension)){
            return avaiableReader.get(extension);
        }else {
            throw new BibliographyException("File reader haven't been implemented yet");
        }
    }
}
