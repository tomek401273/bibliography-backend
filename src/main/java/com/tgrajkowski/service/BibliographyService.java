package com.tgrajkowski.service;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.ReturnMainObject;
import com.tgrajkowski.model.authors.BibliographyReturn;
import com.tgrajkowski.model.authors.PublicationReturn;
import com.tgrajkowski.service.file.CheckFileService;
import com.tgrajkowski.service.file.read.BibilographyCitationService;
import com.tgrajkowski.service.file.read.publication.PublicationCitationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class BibliographyService {
    @Autowired
    private DivideFileService divideFileService;

    @Autowired
    private PublicationCitationServiceImpl publicationCitationServiceImpl;

    @Autowired
    private BibilographyCitationService bibilographyCitationService;

    @Autowired
    private CheckFileService checkFileService;

    public ReturnMainObject checkBibiographyCompatibility(MultipartFile multipartFile) throws BibliographyException {
        ReturnMainObject returnMainObject = new ReturnMainObject();
        long start = System.currentTimeMillis();
        List<String> lines = checkFileService.readFile(multipartFile);
        List<String> publicationLines = divideFileService.getBibiographyLines(lines);
        List<String> mgrLines = divideFileService.getDiplomaLines(lines);
        List<String> mgrLines1 = new ArrayList<>(mgrLines.subList(0, (mgrLines.size()) / 2));
        List<String> mgrLines2 = new ArrayList<>(mgrLines.subList((mgrLines.size()) / 2, mgrLines.size()));
        List<String> publicationLines1 = new ArrayList<>(publicationLines.subList(0, (publicationLines.size()) / 2));
        List<String> publicationLines2 = new ArrayList<>(publicationLines.subList((publicationLines.size()) / 2, publicationLines.size()));

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<BibliographyReturn> bibliographyReturn1 = executor.submit(() -> bibilographyCitationService.validate(mgrLines, publicationLines1));
        Future<BibliographyReturn> bibliographyReturn2 = executor.submit(() -> bibilographyCitationService.validate(mgrLines, publicationLines2));
        Future<PublicationReturn> publicationReturn1 = executor.submit(() -> new PublicationReturn(publicationCitationServiceImpl.validate(mgrLines1, publicationLines)));
        Future<PublicationReturn> publicationReturn2 = executor.submit(() -> new PublicationReturn(publicationCitationServiceImpl.validate(mgrLines2, publicationLines)));
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ignored) {
        }

        PublicationReturn publicationReturnMain = new PublicationReturn();
        try {
            BibliographyReturn bibliographyReturnMain = bibliographyReturn1.get();
            bibliographyReturnMain.getDuplicates().addAll(bibliographyReturn2.get().getDuplicates());
            bibliographyReturnMain.setCountOfPublication(bibliographyReturnMain.getCountOfPublication() + bibliographyReturn2.get().getCountOfPublication());
            bibliographyReturnMain.getPublicationsNotUsed().addAll(bibliographyReturn2.get().getPublicationsNotUsed());
            returnMainObject.setBibliographyReturn(bibliographyReturnMain);
            publicationReturnMain = publicationReturn1.get();
            publicationReturnMain.getPublications().addAll(publicationReturn2.get().getPublications());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        returnMainObject.setPublicationReturn(publicationReturnMain);
        returnMainObject.setCalculationTime(System.currentTimeMillis() - start);
        return returnMainObject;
    }
}
