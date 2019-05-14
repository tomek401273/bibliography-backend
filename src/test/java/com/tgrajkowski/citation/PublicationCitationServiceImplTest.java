package com.tgrajkowski.citation;

import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.service.file.read.publication.PublicationCitationService;
import com.tgrajkowski.service.file.read.publication.PublicationCitationServiceImpl;
import com.tgrajkowski.service.file.read.ReadFileServiceTxt;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PublicationCitationServiceImplTest {
    @Test
    public void test1() {
        PublicationCitationService citationService = new PublicationCitationServiceImpl();
        ReadFileServiceTxt readFileServiceTxt = new ReadFileServiceTxt();
        List<String> text =readFileServiceTxt.readFile("file/text.txt");
        List<String> mgr =readFileServiceTxt.readFile("file/mgr.txt");
        Set<Publication> validate= citationService.validate(mgr, text);
        validate.forEach(System.out::println);
        List<Publication> list = new ArrayList<>(validate);

        List<Publication> expeted = new ArrayList<>();
        expeted.add(new Publication("NCIM", 4960));
        expeted.add(new Publication("Nakiboğlu", 2001));
        expeted.add(new Publication("Thomson", 2014));
        expeted.add(new Publication("de Carvalho", 2008));
        expeted.add(new Publication("van den Burg", 2003));

//        Assert.assertEquals("NCIM", list.get(0).getAuthorName());
        Assert.assertArrayEquals(new List[]{list}, new List[]{expeted});
    }

}
