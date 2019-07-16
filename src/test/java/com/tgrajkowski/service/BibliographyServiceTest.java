package com.tgrajkowski.service;

import com.tgrajkowski.MultipartFilesForTests;
import com.tgrajkowski.Spring5JUnit4ConcurrentTest;
import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.ReturnMainObject;
import com.tgrajkowski.model.job.JobDaoProxy;
import com.tgrajkowski.model.job.JobDto;
import com.tgrajkowski.service.file.CheckFileService;
import com.tgrajkowski.service.file.read.BibilographyCitationService;
import com.tgrajkowski.service.file.read.publication.PublicationCitationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Spring5JUnit4ConcurrentTest.SimpleConfiguration.class)
public class BibliographyServiceTest {
    @InjectMocks
//    @Autowired
    private BibliographyService bibliographyService;

    @Mock
    private JobDaoProxy jobDaoProxy;

//    @Mock
//    private DivideFileService divideFileService;
//
//    @Mock
//    private PublicationCitationServiceImpl publicationCitationServiceImpl;
//
//    @Mock
//    private BibilographyCitationService bibilographyCitationService;
//
//    @Mock
//    private CheckFileService checkFileService;

    @Test
    public void checkBibiographyCompatibility() throws BibliographyException {
        // Given
        MultipartFile file = MultipartFilesForTests.txt();
        String login = "tomek";

//        Mockito.when(jobDaoProxy.saveNewJob(Mockito.any(JobDto.class))).thenReturn(new JobDto());

        ReturnMainObject returnMainObject = bibliographyService.checkBibiographyCompatibility(file, login);
        System.out.println(returnMainObject.toString());

    }
}
