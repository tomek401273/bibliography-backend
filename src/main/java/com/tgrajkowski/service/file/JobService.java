package com.tgrajkowski.service.file;

import com.tgrajkowski.model.job.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobDaoProxy jobDaoProxy;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public JobsDaily countJobsInEachDay() {
        List<JobDaily> jobDailyList = jobDaoProxy.getJobsForEachDay();
        List<String> dateList = jobDailyList.stream().map(jobDaily -> simpleDateFormat.format(jobDaily.getDate())).collect(Collectors.toList());
        List<Integer> countList = jobDailyList.stream().map(JobDaily::getCount).collect(Collectors.toList());
        return new JobsDaily(dateList, countList);
    }

    public List<JobsForDay> countJobsInEachDay2() {
        List<JobDaily> jobDailyList = jobDaoProxy.getJobsForEachDay();
        return jobDailyList.stream()
                .map(jobDaily -> new JobsForDay(jobDaily.getDate().getTime(), jobDaily.getCount()))
                .collect(Collectors.toList());
    }


    public JobDto saveNewJob(String login, String fileName) {
        JobDto jobDto = new JobDto();
        jobDto.setUser(login);
        jobDto.setTitle(fileName);
        jobDto.setDate(new Date());
        return jobDaoProxy.saveNewJob(jobDto);
    }
}
