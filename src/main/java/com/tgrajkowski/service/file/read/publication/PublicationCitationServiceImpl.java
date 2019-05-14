package com.tgrajkowski.service.file.read.publication;

import com.tgrajkowski.model.authors.Publication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PublicationCitationServiceImpl extends PublicationCitationService{

    @Override
    String joinMgrLines(List<String> mgrLines) {
        StringJoiner joiner = new StringJoiner(" ");
        mgrLines.forEach(joiner::add);
        return joiner.toString();
    }

    public Set<String> createMatchedCit(List<String> singleCit) {
        Set<String> matchedCit = new TreeSet<>();
        for (String cit : singleCit) {
            for (String regex : PublicationCitationReqex.regexList) {
                Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(cit);
                if (matcher.find()) {
                    matchedCit.add(cit);
                    break;
                }
            }
        }
        return matchedCit;
    }

    public Set<Publication> createPublication(Set<String> publications) {
        Set<Publication> publicationsPub = new TreeSet<>();
        for (String pub : publications) {
            int endIndexName;
            if (pub.contains(" i ")) {
                endIndexName = pub.indexOf(" i ");
            } else {
                endIndexName = pub.lastIndexOf(" ");
            }
            String name = pub.substring(0, endIndexName);

            int indexFirstPubYear = pub.lastIndexOf(" ");
            int publicationYear = 0;
            try {
                publicationYear = Integer.parseInt(pub.substring(indexFirstPubYear).trim());
            } catch (NumberFormatException e) {e.printStackTrace();}
            Publication publication = new Publication(name, publicationYear);
            publicationsPub.add(publication);
        }
        return publicationsPub;
    }


    public List<String> recursiveRemoveMultipleCitationFromOneBracketsCit(String multipleCit, List<String> singleCitTest) {
        if (!multipleCit.contains(",")) singleCitTest.add(multipleCit);
        else {
            String toBeRepkacement = multipleCit.substring(multipleCit.lastIndexOf(","));
            multipleCit = multipleCit.replace(toBeRepkacement, "");
            toBeRepkacement = toBeRepkacement.replace(", ", "");
            singleCitTest.add(toBeRepkacement);
            recursiveRemoveMultipleCitationFromOneBracketsCit(multipleCit, singleCitTest);
        }
        return singleCitTest;
    }

    public List<String> recursiveRemoving(String mgr, List<String> citationsLoc) {
        if (mgr.contains("(")){
            String toBeReplaced = mgr.substring(mgr.indexOf("("), mgr.indexOf(")") + 1);
            if (toBeReplaced.matches("\\([0-9\\)]+\\)")) {
                String regexBeforeBrackets = "\\w+\\s[i]\\s[i][n][.]\\s[(]|\\w+\\s[(]|\\w+\\s[i]\\s\\w+\\s[(]";
                Matcher matcher = Pattern.compile(regexBeforeBrackets, Pattern.UNICODE_CHARACTER_CLASS).matcher(mgr);
                if (matcher.find()) {
                    String name = mgr.substring( matcher.start(), matcher.end());
                    String onlyName = name.substring(0, name.indexOf(" "));
                    citationsLoc.add(onlyName + " " + toBeReplaced);
                    name = name.substring(0, name.length() - 1);
                    toBeReplaced = name + toBeReplaced;
                }
            } else {
                citationsLoc.add(toBeReplaced);
            }
            mgr = mgr.replace(toBeReplaced, "||||replacement||||");
            recursiveRemoving(mgr, citationsLoc);
        }
        return citationsLoc;
    }

    public List<String> remioveBrackets(List<String> newStringList) {
        return newStringList.stream()
                .map(s -> s.replace("(", ""))
                .map(s -> s.replace(")", ""))
                .collect(Collectors.toList());
    }
}
