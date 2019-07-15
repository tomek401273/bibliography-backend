package com.tgrajkowski.service.file.read.publication;

import com.tgrajkowski.model.authors.Publication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PublicationCitationServiceImpl extends PublicationCitationService {

    @Override
    String joinMgrLines(List<String> mgrLines) {
      return String.join(" ", mgrLines);
    }

    @Override
    public Set<String> createMatchedCit(List<String> singleCit) {
        Set<String> matchedCit = new TreeSet<>();
        singleCit.forEach(cit->{
            for (String regex : PublicationCitationReqex.regexList) {
                Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(cit);
                if (matcher.find()) {
                    matchedCit.add(cit);
                    break;
                }
            }
        });
        return matchedCit;
    }

    @Override
    public Set<Publication> createPublication(Set<String> publications) {
        Set<Publication> publicationsPub = new TreeSet<>();
        for (String pub : publications) {
            int endIndexName;
            if (pub.contains(" i ")) {
                endIndexName = pub.indexOf(" i ");
            } else {
                endIndexName = pub.lastIndexOf(" ");
            }
            try {
                int publicationYear = Integer.parseInt(pub.substring(pub.lastIndexOf(" ")).trim());
                publicationsPub.add(new Publication(pub.substring(0, endIndexName), publicationYear));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return publicationsPub;
    }

    @Override
    public List<String> recursiveRemoveMultipleCitationFromOneBracketsCit(String multipleCit, List<String> singleCitTest) {
        if (multipleCit.contains(",")) {
            String toBeRepkacement = multipleCit.substring(multipleCit.lastIndexOf(","));
            multipleCit = multipleCit.replace(toBeRepkacement, "");
            toBeRepkacement = toBeRepkacement.replace(", ", "");
            singleCitTest.add(toBeRepkacement);
            recursiveRemoveMultipleCitationFromOneBracketsCit(multipleCit, singleCitTest);
        } else singleCitTest.add(multipleCit);
        return singleCitTest;
    }


    @Override
    public List<String> removeBrackets(List<String> newStringList) {
        return newStringList.stream()
                .map(s -> s.replace("(", ""))
                .map(s -> s.replace(")", ""))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> recursiveRemoving(String mgr, List<String> citationsLoc) {
        if (mgr.contains("(")) {
            String toBeReplaced = mgr.substring(mgr.indexOf("("), mgr.indexOf(")") + 1);
            if (toBeReplaced.matches("\\([0-9\\)]+\\)")) {
                String regexBeforeBrackets = "\\w+\\s[i]\\s[i][n][.]\\s[(]|\\w+\\s[(]|\\w+\\s[i]\\s\\w+\\s[(]";
                Matcher matcher = Pattern.compile(regexBeforeBrackets, Pattern.UNICODE_CHARACTER_CLASS).matcher(mgr);
                if (matcher.find()) {
                    String name = mgr.substring(matcher.start(), matcher.end());
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

}
