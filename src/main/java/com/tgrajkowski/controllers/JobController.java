package com.tgrajkowski.controllers;

import com.tgrajkowski.model.job.JobDto;
import com.tgrajkowski.model.job.JobsDaily;
import com.tgrajkowski.model.job.JobsForDay;
import com.tgrajkowski.service.file.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/count/for/each/day")
    public JobsDaily countJobsInEachDay() {
        return jobService.countJobsInEachDay();
    }

    @RequestMapping(value = "/count/for/each/day2")
    public List<JobsForDay> countJobsInEachDay2() {
        return jobService.countJobsInEachDay2();
    }

    @RequestMapping(value = "/save/new", method = RequestMethod.POST)
    public JobDto saveNewJobDto(@RequestParam String login, @RequestParam String fileName){
        return jobService.saveNewJob(login, fileName);
    }
}
