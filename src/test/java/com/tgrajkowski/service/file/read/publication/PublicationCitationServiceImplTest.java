package com.tgrajkowski.service.file.read.publication;

import com.tgrajkowski.model.authors.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicationCitationServiceImplTest {
    @Autowired
    private PublicationCitationServiceImpl publicationCitationService;

    @Test
    public void joinMgrLines() {
        List<String> lines = new ArrayList<>();
        lines.add("line1");
        lines.add("line2");
        lines.add("line3");

        String result = publicationCitationService.joinMgrLines(lines);
        System.out.println(result);
        Assert.assertEquals("line1 line2 line3", result);
    }

    @Test
    public void createMatchedCit() {
        List<String> pub = new ArrayList<>();
        pub.add("Prakash i in. 2009");
        pub.add("Babu i Satyanarayana 1995");
        pub.add("Babu Satyanarayana");
        pub.add("Babu");
        Set<String> results = publicationCitationService.createMatchedCit(pub);
        System.out.println("results: " + results.size());
        results.forEach(System.out::println);
        List<String> resultsArrayList = new ArrayList<>(results);
        Assert.assertEquals("Babu i Satyanarayana 1995", resultsArrayList.get(0));
        Assert.assertEquals("Prakash i in. 2009", resultsArrayList.get(1));
    }

    @Test
    public void createPublication() {
        Set<String> matchedCit = new HashSet<>();
        matchedCit.add("Prakash i in. 2009, Grajkowski i in. 2019");
        matchedCit.add("Babu i Satyanarayana 1995");
        Set<Publication> publicationSet = publicationCitationService.createPublication(matchedCit);
        ArrayList<Publication> publicationArrayList = new ArrayList<>(publicationSet);
        publicationArrayList.forEach(System.out::println);

        Assert.assertEquals(new Publication("Babu", 1995), publicationArrayList.get(0));
        Assert.assertEquals(new Publication("Prakash", 2019), publicationArrayList.get(1));

    }

    @Test
    public void recursiveRemoveMultipleCitationFromOneBracketsCit() {
        List<String> results = publicationCitationService.recursiveRemoveMultipleCitationFromOneBracketsCit("Babu i Satyanarayana 1995, Grajkowski i in. 2019", new ArrayList<>());
        Assert.assertEquals(2, results.size());
        Assert.assertEquals("Grajkowski i in. 2019", results.get(0));
        Assert.assertEquals("Babu i Satyanarayana 1995", results.get(1));
    }

    @Test
    public void remioveBrackets() {
        List<String> pub = new ArrayList<>();
        pub.add("(Prakash i in. 2009, Grajkowski i in. 2019)");
        pub.add("(Babu i Satyanarayana 1995)");
        List<String> result = publicationCitationService.removeBrackets(pub);

        Assert.assertEquals("Prakash i in. 2009, Grajkowski i in. 2019", result.get(0));
        Assert.assertEquals("Babu i Satyanarayana 1995", result.get(1));
    }

    @Test
    public void recursiveRemoving() {
        String mgr = "Enzymy amylolityczne są produkowane przez  bakterie: Chromohalobacter sp. TVSP 101 (Prakash i in. 2009, Grajkowski i in. 2019), Bacillus coagulans (Babu i Satyanarayana 1995)";
        List<String> result = publicationCitationService.recursiveRemoving(mgr, new ArrayList<>());

        Assert.assertEquals("(Prakash i in. 2009, Grajkowski i in. 2019)", result.get(0));
        Assert.assertEquals("(Babu i Satyanarayana 1995)", result.get(1));
    }

}
