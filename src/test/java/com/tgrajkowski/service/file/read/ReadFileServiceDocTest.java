package com.tgrajkowski.service.file.read;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.model.BibliographyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadFileServiceDocTest {
    @Autowired
    private ReadFileServiceTxt readFileServiceTxt;
    @Autowired
    private ReadFileServiceDoc readFileServiceDoc;
    @Autowired
    private ReadFileServiceDocx readFileServiceDocx;

    @Test
    public void readFile() throws BibliographyException {
        MultipartFile file = MultipartFilesForTests.txt();
        List<String> stringList = readFileServiceTxt.readFile(file);
        System.out.println("size: " + stringList.size());

        System.out.println(stringList.get(stringList.size() - 1));
        Assert.assertEquals(314, stringList.size());
        Assert.assertEquals("Zanphorlin, L. M., Facchini, F. D. A., Vasconcelos, F., Bonugli-Santos, R. C., Rodrigues, A., Sette, L. D., ... & Bonilla-Rodriguez, G. O. (2010). Production, partial characterization, and immobilization in alginate beads of an alkaline protease from a new thermophilic fungus Myceliophthora sp. The Journal of Microbiology, 48(3), 331-336.",
                stringList.get(stringList.size() - 1));
    }

    @Test
    public void readFileDoc() throws BibliographyException {
        MultipartFile file = MultipartFilesForTests.doc();
        List<String> stringList = readFileServiceDoc.readFile(file);
        System.out.println("size: " + stringList.size());

        System.out.println(stringList.get(stringList.size() - 1));
        Assert.assertEquals(295, stringList.size());

    }

    @Test
    public void readFileDocx() throws BibliographyException {
        MultipartFile file = MultipartFilesForTests.docx();
        List<String> stringList = readFileServiceDocx.readFile(file);
        System.out.println("size: " + stringList.size());

        System.out.println(stringList.get(stringList.size() - 1));
        Assert.assertEquals(295, stringList.size());

    }
}
