package com.tgrajkowski.service;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.ReturnMainObject;
import com.tgrajkowski.model.authors.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BibliographyServiceTest {
    @Autowired
    private BibliographyService bibliographyService;

    @Test
    public void checkBibiographyCompatibility() throws BibliographyException {
        // Given
        MultipartFile file = MultipartFilesForTests.txt();
        String login = "tomek";
        //When
        ReturnMainObject returnMainObject = bibliographyService.checkBibiographyCompatibility(file, login);
        System.out.println(returnMainObject.toString());
        // Then
        Assert.assertEquals(new ArrayList<>(returnMainObject.getBibliographyReturn().getDuplicates()).get(0), new Publication("Zanphorlin", 2010));
        Assert.assertEquals(157, returnMainObject.getBibliographyReturn().getCountOfPublication());
        assertTrue(returnMainObject.getBibliographyReturn().getPublicationsNotUsed().contains(new Publication("Zan1", 2077)));
        Assert.assertEquals(4, returnMainObject.getBibliographyReturn().getPublicationsNotUsed().size());
        Assert.assertEquals(5, returnMainObject.getPublicationReturn().getPublications().size());
        assertTrue(returnMainObject.getPublicationReturn().getPublications().contains(new Publication("Thomson", 2014)));
    }
}
