package com.tgrajkowski.service.file;

import com.tgrajkowski.model.job.JobDaoIml;
import com.tgrajkowski.model.job.JobDto;
import com.tgrajkowski.model.job.JobDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobDaoIml jobDaoIml;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public JobDtos countJobsInEachDay() {
        List<JobDto> jobDtoList = jobDaoIml.findPipelinedStatements();
        List<String> dateList = jobDtoList.stream().map(jobDto -> simpleDateFormat.format(jobDto.getDate())).collect(Collectors.toList());
        List<Integer> countList = jobDtoList.stream().map(JobDto::getCount).collect(Collectors.toList());
        return new JobDtos(dateList, countList);
    }
}
