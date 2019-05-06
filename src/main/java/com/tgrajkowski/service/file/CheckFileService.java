package com.tgrajkowski.service.file;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import com.tgrajkowski.model.file.formats.FileFormats;
import com.tgrajkowski.service.DivideFileService;
import com.tgrajkowski.service.file.read.ReadFactory;
import com.tgrajkowski.service.file.read.ReadFileService;
import com.tgrajkowski.service.file.read.ReadFileServiceTxt;
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

    public void readFile(MultipartFile multipartFile) throws IOException, BibliographyException {
        String temp = "/home/tomek/Documents/samples2/bibliography-backend/src/main/upload/" + multipartFile.getOriginalFilename();
        try (InputStream inputStream = multipartFile.getInputStream()) {

            Files.copy(inputStream, Paths.get(temp), StandardCopyOption.REPLACE_EXISTING);
            String fileExtension = getFileExtension(multipartFile.getOriginalFilename());
            canFileBeProcessed(fileExtension);


            ReadFileService readFileService = readFactory.createReadFileService(fileExtension);
            List<String> lines = readFileService.readFile(multipartFile);
            lines.forEach(System.out::println);


            long start = System.currentTimeMillis();

            List<String> publicationLines = divideFileService.getBibiographyLines(lines);
//
//
            PublicationCreator publicationCreator = new PublicationCreator();
            Set<Publication> publications = publicationCreator.createPublications(publicationLines);
//
            System.out.println("cumberOfPublication: "+publications.size());
//

            List<String> mgrLines = divideFileService.getDiplomaLines(lines);



            String allMgr = "";
            for (String line: mgrLines) {
                allMgr = allMgr+" "+line;
            }
            System.out.println("countLetters:"+allMgr.length());
            int countNotUsedPublication = 0;
            for (Publication publication: publications) {
                if (allMgr.contains(publication.getAuthorName())) {
                } else {
                    System.out.println(publication.toString());
                    countNotUsedPublication++;
                }
            }

            System.out.println("countNotUsedPublication: "+ countNotUsedPublication);
            long end = System.currentTimeMillis();
            long calculationTime = end - start;
            System.out.println("calculationTime: "+calculationTime+" [ms]");

        } catch (IOException e) {

            e.printStackTrace();
            throw new IOException(e);
        }
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
