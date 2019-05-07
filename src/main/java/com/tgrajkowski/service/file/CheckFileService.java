package com.tgrajkowski.service.file;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import com.tgrajkowski.model.file.formats.FileFormats;
import com.tgrajkowski.service.DivideFileService;
import com.tgrajkowski.service.file.read.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CheckFileService {
    @Autowired
    private ReadFactory readFactory;

    @Autowired
    private DivideFileService divideFileService;

    @Autowired
    private PublicationCitationService publicationCitationService;

    @Autowired
    private BibilographyCitationService bibilographyCitationService;

    public void readFile(MultipartFile multipartFile) throws IOException, BibliographyException {
        String temp = "/home/tomek/Documents/samples2/bibliography-backend/src/main/upload/" + multipartFile.getOriginalFilename();
        long start = System.currentTimeMillis();
        try (InputStream inputStream = multipartFile.getInputStream()) {

            Files.copy(inputStream, Paths.get(temp), StandardCopyOption.REPLACE_EXISTING);
            String fileExtension = getFileExtension(multipartFile.getOriginalFilename());
            canFileBeProcessed(fileExtension);


            ReadFileService readFileService = readFactory.createReadFileService(fileExtension);
            List<String> lines = readFileService.readFile(multipartFile);



            List<String> publicationLines = divideFileService.getBibiographyLines(lines);
            List<String> mgrLines = divideFileService.getDiplomaLines(lines);

//            Thread thread1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
            bibilographyCitationService.validate(mgrLines, publicationLines);



            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            publicationCitationService.Validate(mgrLines, publicationLines);

        } catch (IOException e) {

            e.printStackTrace();
            throw new IOException(e);
        }
        long end = System.currentTimeMillis();
        long calculationTime = end - start;
        System.out.println("calculationTime: "+calculationTime+" [ms]");
    }

    public String getFileExtension(String fullFileName) throws BibliographyException {
        int lastDot = fullFileName.lastIndexOf(".");
        if (lastDot == -1) {
            throw new BibliographyException("File without extension");
        }
        return fullFileName.substring(lastDot + 1);
    }

    public boolean canFileBeProcessed(String extension) throws BibliographyException {
        try {
            FileFormats.valueOf(extension);
        } catch (IllegalArgumentException e) {
            throw new BibliographyException("Files with this extension: " + extension + " can't be processed");
        }
        return true;
    }
}
