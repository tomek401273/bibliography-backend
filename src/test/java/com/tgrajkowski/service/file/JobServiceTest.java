package com.tgrajkowski.service.file;

import com.tgrajkowski.model.job.JobDaily;
import com.tgrajkowski.model.job.JobDaoProxy;
import com.tgrajkowski.model.job.JobsDaily;
import com.tgrajkowski.model.job.JobsForDay;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobDaoProxy jobDaoProxy;

    @Test
    public void countJobsInEachDay() {
        // Given
        Calendar calendar = new GregorianCalendar(2019,6,16);
        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date beforeYesterDay = calendar.getTime();

        List<JobDaily> jobDailyList = new ArrayList<>();
        jobDailyList.add(new JobDaily(today, 8));
        jobDailyList.add(new JobDaily(yesterday, 4));
        jobDailyList.add(new JobDaily(beforeYesterDay, 2));
        //When
        Mockito.when(jobDaoProxy.getJobsForEachDay()).thenReturn(jobDailyList);

        JobsDaily jobsDaily =jobService.countJobsInEachDay();

        Assert.assertEquals(jobsDaily.getDateList().get(0),"2019-07-16 00:00:00");
        Assert.assertEquals(jobsDaily.getDateList().get(1),"2019-07-15 00:00:00");
        Assert.assertEquals(jobsDaily.getDateList().get(2),"2019-07-14 00:00:00");

        Assert.assertEquals(jobsDaily.getCounts().get(0), Integer.valueOf(8));
        Assert.assertEquals(jobsDaily.getCounts().get(1), Integer.valueOf(4));
        Assert.assertEquals(jobsDaily.getCounts().get(2), Integer.valueOf(2));
    }

    @Test
    public void countJobsInEachDay2() {
        // Given
        Calendar calendar = new GregorianCalendar(2019,6,16);
        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date beforeYesterDay = calendar.getTime();

        List<JobDaily> jobDailyList = new ArrayList<>();
        jobDailyList.add(new JobDaily(today, 8));
        jobDailyList.add(new JobDaily(yesterday, 4));
        jobDailyList.add(new JobDaily(beforeYesterDay, 2));
        // When
        Mockito.when(jobDaoProxy.getJobsForEachDay()).thenReturn(jobDailyList);
        List<JobsForDay> jobsForDayList = jobService.countJobsInEachDay2();
        // Then
        Assert.assertEquals(jobsForDayList.get(0).getCount(), 8);
        Assert.assertEquals(jobsForDayList.get(1).getCount(), 4);
        Assert.assertEquals(jobsForDayList.get(2).getCount(), 2);

        Assert.assertEquals(jobsForDayList.get(0).getDate(), today.getTime());
        Assert.assertEquals(jobsForDayList.get(1).getDate(), yesterday.getTime());
        Assert.assertEquals(jobsForDayList.get(2).getDate(), beforeYesterDay.getTime());
    }



}
