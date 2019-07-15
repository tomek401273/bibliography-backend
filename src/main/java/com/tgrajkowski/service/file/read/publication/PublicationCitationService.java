package com.tgrajkowski.service.file.read.publication;

import com.tgrajkowski.model.PublicationAndDupl;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import java.util.*;

public abstract class PublicationCitationService {
    abstract String joinMgrLines(List<String> mgrLines);
    abstract Set<String> createMatchedCit(List<String> singleCit);
    abstract Set<Publication> createPublication(Set<String> publications);
    abstract List<String> recursiveRemoveMultipleCitationFromOneBracketsCit(String multipleCit, List<String> singleCitTest);
    abstract List<String> recursiveRemoving(String mgr, List<String> citationsLoc);
    abstract List<String> removeBrackets(List<String> newStringList);

    public Set<Publication> validate(List<String> mgrLines, List<String> publicationLines ) {
        String allMgr = joinMgrLines(mgrLines);
        List<String> citations = recursiveRemoving(allMgr, new ArrayList<>());
        citations = removeBrackets(citations);
        List<String> singleCit = new ArrayList<>();
        citations.forEach(s -> singleCit.addAll(recursiveRemoveMultipleCitationFromOneBracketsCit(s,  new ArrayList<>())));

        Set<String> matchedCit = createMatchedCit(singleCit);
        Set<Publication> publicationSet = createPublication(matchedCit);
        PublicationCreator publicationCreator = new PublicationCreator();

        PublicationAndDupl publicationAndDupl =  publicationCreator.createPublications(publicationLines);
        Set<Publication> publicationsBib = publicationAndDupl.getPublicationSet();
        publicationSet.removeAll(publicationsBib);
        return  publicationSet;
    }
}
