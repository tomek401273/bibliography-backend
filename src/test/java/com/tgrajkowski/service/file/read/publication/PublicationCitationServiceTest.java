package com.tgrajkowski.service.file.read.publication;

import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.model.authors.PublicationReturn;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicationCitationServiceTest {

    @Autowired
    private PublicationCitationServiceImpl publicationCitationService;

    @Test
    public void validate() {
        List<String> mgrLines = new ArrayList<>();
        mgrLines.add("Enzymy amylolityczne są produkowane przez  bakterie: ");
        mgrLines.add("Chromohalobacter sp. TVSP 101 (Prakash i in. 2009, Grajkowski i in. 2019), ");
        mgrLines.add("Bacillus coagulans (Babu i Satyanarayana 1995)");


        List<String> publicationLines = new ArrayList<>();
        publicationLines.add("Babu, K. R., & Satyanarayana, T. (1995). α-Amylase production by thermophilic Bacillus coagulans in solid state fermentation. Process Biochemistry, 30(4), 305-309.");
        publicationLines.add("Prakash, B., Vidyasagar, M., Madhukumar, M. S., Muralikrishna, G., & Sreeramulu, K. (2009). Production, purification, and characterization of two extremely halotolerant, thermostable, and alkali-stable α-amylases from Chromohalobacter sp. TVSP 101. Process Biochemistry, 44(2), 210-215.");


        Set<Publication> validate = publicationCitationService.validate(mgrLines, publicationLines);
        validate.forEach(System.out::println);

        Assert.assertEquals(new Publication("Grajkowski", 2019), new ArrayList<>(validate).get(0));
    }
}
