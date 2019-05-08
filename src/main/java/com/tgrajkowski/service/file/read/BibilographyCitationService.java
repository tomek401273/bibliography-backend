package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.PublicationAndDupl;
import com.tgrajkowski.model.authors.BibliographyReturn;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BibilographyCitationService {
    public BibliographyReturn validate(List<String> mgrLines, List<String> publicationLines ){
        BibliographyReturn bibliographyReturn = new BibliographyReturn();

        PublicationCreator publicationCreator = new PublicationCreator();
        PublicationAndDupl publicationAndDupl = publicationCreator.createPublications(publicationLines);
        Set<Publication> publications = publicationAndDupl.getPublicationSet();
        bibliographyReturn.setDuplicates(publicationAndDupl.getDuplicates());
//
//        System.out.println("cumberOfPublication: "+publications.size());
        bibliographyReturn.setCountOfPublication(publications.size());
//
        String allMgr = "";
        for (String line: mgrLines) {
            allMgr = allMgr+" "+line;
        }
//        System.out.println("countLetters:"+allMgr.length());
        int countNotUsedPublication = 0;
        for (Publication publication: publications) {
            if (allMgr.contains(publication.getAuthorName())) {
            } else {
                bibliographyReturn.getPublicationsNotUsed().add(publication);
//                System.out.println(publication.toString());
                countNotUsedPublication++;
            }
        }

//        System.out.println("countNotUsedPublication: "+ countNotUsedPublication);
//
        return bibliographyReturn;
    }
}
