package com.tgrajkowski.controllers;

import com.tgrajkowski.model.job.JobsDaily;
import com.tgrajkowski.service.file.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
