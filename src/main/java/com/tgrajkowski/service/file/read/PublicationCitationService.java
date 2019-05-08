package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationCreator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublicationCitationService {
    private static List<String> citations = new ArrayList<>();
    private static List<String> singleCit = new ArrayList<>();

    public void Validate(List<String> mgrLines, List<String> publicationLines ) {


        String allMgr = "";
        for (String line : mgrLines) {
            allMgr = allMgr + " " + line;
        }

        recursiveRemoving(allMgr);
        remioveBrackets();

        for (String citation : citations) {
            recursiveRemoveMultipleCitationFromOneBracketsCit(citation);
        }

        Set<String> matchedCit = new TreeSet<>();
        Set<String> notMatchedCit = new HashSet<>();
        for (String cit : singleCit) {
            List<String> regexList = new ArrayList<>();
            regexList.add("\\w+\\s\\w+\\s\\w+\\s[0-9]{4,4}");
            regexList.add("\\w+\\s[0-9]{4,4}");
            regexList.add("\\w+\\s[i]\\s[i][n][.]\\s[0-9]{4,4}");
            regexList.add("\\w+\\s[i]\\s\\w+\\s[0-9]{4,4}");

            for (String regex : regexList) {
                Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(cit);
                if (matcher.find()) {
                    matchedCit.add(cit);
                    notMatchedCit.remove(cit);
                    break;
                } else {
                    notMatchedCit.add(cit);
                }
            }

        }

//        singleCit.stream().forEach(System.out::println);

//        matchedCit.stream().forEach(System.out::println);
//        System.out.println(matchedCit.size());
        Set<Publication> publicationSet = createPublication(matchedCit);

        PublicationCreator publicationCreator = new PublicationCreator();
        Set<Publication> publicationsBib = publicationCreator.createPublications(publicationLines);
        publicationSet.removeAll(publicationsBib);

        publicationSet.stream().forEach(System.out::println);



//        Scanner scanner = new Scanner(System.in);
//        for (String notMatch: notMatchedCit) {
//            scanner.nextLine();
//            System.out.println(notMatch);
//
//        }
        citations = new ArrayList<>();
        singleCit = new ArrayList<>();
    }

    public static Set<Publication> createPublication(Set<String> publications) {
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
            String pubYear = pub.substring(indexFirstPubYear, pub.length());
            int publicationYear = 0;
            try {
                pubYear = pubYear.trim();
                publicationYear = Integer.parseInt(pubYear);
            } catch (NumberFormatException e) {
                System.out.println(e);
                System.out.println(pub + ":" + pubYear);

            }
            Publication publication = new Publication(name, publicationYear);
            publicationsPub.add(publication);
        }
        return publicationsPub;
    }


    public static void recursiveRemoveMultipleCitationFromOneBracketsCit(String multipleCit) {
        if (!multipleCit.contains(",")) {
            singleCit.add(multipleCit);
        } else {
            int lastCommaIndex = multipleCit.lastIndexOf(",");
            String tobeRepkacement = multipleCit.substring(lastCommaIndex, multipleCit.length());
            multipleCit = multipleCit.replace(tobeRepkacement, "");
            tobeRepkacement = tobeRepkacement.replace(", ", "");
            singleCit.add(tobeRepkacement);
            recursiveRemoveMultipleCitationFromOneBracketsCit(multipleCit);
        }
    }

    public static String recursiveRemoving(String mgr) {
        if (!mgr.contains("(")) {
//            System.out.println(mgr);
            return mgr;
        } else {
            int startIndex = mgr.indexOf("(");
            int endIndex = mgr.indexOf(")");
            String replacement = "|||||||";
            String toBeReplaced = "";
            try {
                toBeReplaced = mgr.substring(startIndex, endIndex + 1);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e);
            }
            String regex = "\\([0-9\\)]+\\)";
            if (toBeReplaced.matches(regex)) {
                String regexBeforeBrackets = "\\w+\\s[i]\\s[i][n][.]\\s[(]|\\w+\\s[(]|\\w+\\s[i]\\s\\w+\\s[(]";
                Pattern pattern = Pattern.compile(regexBeforeBrackets, Pattern.UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(mgr);
                if (matcher.find()) {
                    int startName = matcher.start();
                    int endName = matcher.end();
                    String name = mgr.substring(startName, endName);
                    String onlyName = name.substring(0, name.indexOf(" "));
                    citations.add(onlyName + " " + toBeReplaced);
                    name = name.substring(0, name.length() - 1);
                    toBeReplaced = name + toBeReplaced;
                }
            } else {
                citations.add(toBeReplaced);
            }
            mgr = mgr.replace(toBeReplaced, replacement);
            recursiveRemoving(mgr);
            return mgr;
        }
    }

    public static void remioveBrackets() {
        List<String> citationsWithoutBrackets = new ArrayList<>();
        for (String citation : citations) {
            citation = citation.replace("(", "");
            citation = citation.replace(")", "");
            citationsWithoutBrackets.add(citation);
        }
        citations = new ArrayList<>();
        citations.addAll(citationsWithoutBrackets);
    }
}
