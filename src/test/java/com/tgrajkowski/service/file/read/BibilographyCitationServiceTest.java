package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.authors.BibliographyReturn;
import com.tgrajkowski.model.authors.Publication;
import com.tgrajkowski.service.BibliographyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BibilographyCitationServiceTest {

    @Autowired
    private BibilographyCitationService bibilographyCitationService;

    @Test
    public void validateOk() {
        List<String> mgrLines = new ArrayList<>();
        mgrLines.add("Enzymy amylolityczne są produkowane przez  bakterie: ");
        mgrLines.add("Chromohalobacter sp. TVSP 101 (Prakash i in. 2009");
        mgrLines.add("Bacillus coagulans (Babu i Satyanarayana 1995)");


        List<String> publicationLines = new ArrayList<>();
        publicationLines.add("Babu, K. R., & Satyanarayana, T. (1995). α-Amylase production by thermophilic Bacillus coagulans in solid state fermentation. Process Biochemistry, 30(4), 305-309.");
        publicationLines.add("Prakash, B., Vidyasagar, M., Madhukumar, M. S., Muralikrishna, G., & Sreeramulu, K. (2009). Production, purification, and characterization of two extremely halotolerant, thermostable, and alkali-stable α-amylases from Chromohalobacter sp. TVSP 101. Process Biochemistry, 44(2), 210-215.");

        BibliographyReturn bibliographyReturn = bibilographyCitationService.validate(mgrLines, publicationLines);

        System.out.println(bibliographyReturn.toString());
        Assert.assertEquals(0, bibliographyReturn.getDuplicates().size());
        Assert.assertEquals(0, bibliographyReturn.getPublicationsNotUsed().size());
        Assert.assertEquals(2, bibliographyReturn.getCountOfPublication());

    }


    @Test
    public void validateOneDuplicate() {
        List<String> mgrLines = new ArrayList<>();
        mgrLines.add("Enzymy amylolityczne są produkowane przez  bakterie: ");
        mgrLines.add("Chromohalobacter sp. TVSP 101 (Prakash i in. 2009");
        mgrLines.add("Bacillus coagulans (Babu i Satyanarayana 1995)");


        List<String> publicationLines = new ArrayList<>();
        publicationLines.add("Babu, K. R., & Satyanarayana, T. (1995). α-Amylase production by thermophilic Bacillus coagulans in solid state fermentation. Process Biochemistry, 30(4), 305-309.");
        publicationLines.add("Prakash, B., Vidyasagar, M., Madhukumar, M. S., Muralikrishna, G., & Sreeramulu, K. (2009). Production, purification, and characterization of two extremely halotolerant, thermostable, and alkali-stable α-amylases from Chromohalobacter sp. TVSP 101. Process Biochemistry, 44(2), 210-215.");
        publicationLines.add("Prakash, B., Vidyasagar, M., Madhukumar, M. S., Muralikrishna, G., & Sreeramulu, K. (2009). Production, purification, and characterization of two extremely halotolerant, thermostable, and alkali-stable α-amylases from Chromohalobacter sp. TVSP 101. Process Biochemistry, 44(2), 210-215.");

        BibliographyReturn bibliographyReturn = bibilographyCitationService.validate(mgrLines, publicationLines);

        System.out.println(bibliographyReturn.toString());
        Assert.assertEquals(1, bibliographyReturn.getDuplicates().size());
        Assert.assertEquals(0, bibliographyReturn.getPublicationsNotUsed().size());
        Assert.assertEquals(2, bibliographyReturn.getCountOfPublication());
        Assert.assertEquals(new Publication("Prakash", 2009), new ArrayList<>(bibliographyReturn.getDuplicates()).get(0));
    }



    @Test
    public void validateOnePublicationNotUsed() {
        List<String> mgrLines = new ArrayList<>();
        mgrLines.add("Enzymy amylolityczne są produkowane przez  bakterie: ");
        mgrLines.add("Chromohalobacter sp. TVSP 101 (Prakash i in. 2009");
        mgrLines.add("Bacillus coagulans (Babu i Satyanarayana 1995)");

        List<String> publicationLines = new ArrayList<>();
        publicationLines.add("Babu, K. R., & Satyanarayana, T. (1995). α-Amylase production by thermophilic Bacillus coagulans in solid state fermentation. Process Biochemistry, 30(4), 305-309.");
        publicationLines.add("Prakash, B., Vidyasagar, M., Madhukumar, M. S., Muralikrishna, G., & Sreeramulu, K. (2009). Production, purification");
        publicationLines.add("Savitha, S., Sadhasivam, S., Swaminathan, K., & Lin, F. H. (2011). Fungal protease: production, purification and compatibility with laundry detergents and their wash performance. Journal of the Taiwan Institute of Chemical Engineers, 42(2), 298-304.\n");

        BibliographyReturn bibliographyReturn = bibilographyCitationService.validate(mgrLines, publicationLines);

        Assert.assertEquals(0, bibliographyReturn.getDuplicates().size());
        Assert.assertEquals(1, bibliographyReturn.getPublicationsNotUsed().size());
        Assert.assertEquals(3, bibliographyReturn.getCountOfPublication());
        Assert.assertEquals(new Publication("Savitha", 2011), new ArrayList<>(bibliographyReturn.getPublicationsNotUsed()).get(0));

    }
}
