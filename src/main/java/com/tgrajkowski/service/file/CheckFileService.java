package com.tgrajkowski.service.file;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import com.tgrajkowski.model.file.formats.FileFormats;
import com.tgrajkowski.service.DivideFileService;
import com.tgrajkowski.service.file.read.*;
import org.apache.poi.xssf.model.ThemesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    public String readFile(MultipartFile multipartFile) throws IOException, BibliographyException {
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

            List<String> mgrLines1 = new ArrayList<>(mgrLines.subList(0, (mgrLines.size())/2));
            List<String> mgrLines2 = new ArrayList<>(mgrLines.subList((mgrLines.size())/2, mgrLines.size()));

            List<String> publicationLines1 = new ArrayList<>(publicationLines.subList(0, (publicationLines.size())/2));
            List<String> publicationLines2 = new ArrayList<>(publicationLines.subList((publicationLines.size())/2, publicationLines.size()));


            ExecutorService executor = Executors.newFixedThreadPool(2);
            Thread thread11 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start thread1");
                    bibilographyCitationService.validate(mgrLines, publicationLines1);
                    System.out.println("end thread1");
                }
            });
            executor.submit(thread11);

            Thread thread12 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start thread1");
                    bibilographyCitationService.validate(mgrLines, publicationLines2);
                    System.out.println("end thread1");
                }
            });
            executor.submit(thread12);


            //////////////////////////
            Thread thread21 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start thread2");
                    publicationCitationService.Validate(mgrLines1, publicationLines);
                    System.out.println("end thread2");
                }
            });
            executor.submit(thread21);

            Thread thread22 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start thread2");
                    publicationCitationService.Validate(mgrLines2, publicationLines);
                    System.out.println("end thread2");
                }
            });
            executor.submit(thread22);

            executor.shutdown();

            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
            }

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");

        } catch (IOException e) {

            e.printStackTrace();
            throw new IOException(e);
        }
        long end = System.currentTimeMillis();
        long calculationTime = end - start;
        System.out.println("calculationTime: "+calculationTime+" [ms]");
        return "calculationTime: "+calculationTime+" [ms]";
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
