package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BibilographyCitationService {
    public void validate(List<String> mgrLines, List<String> publicationLines ){


        PublicationCreator publicationCreator = new PublicationCreator();
        Set<Publication> publications = publicationCreator.createPublications(publicationLines);
//
        System.out.println("cumberOfPublication: "+publications.size());
//
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

    }
}
