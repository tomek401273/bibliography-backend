package com.tgrajkowski.controllers;

import com.tgrajkowski.model.job.Job;
import com.tgrajkowski.model.job.JobDto;
import com.tgrajkowski.model.job.JobDtos;
import com.tgrajkowski.service.file.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/count/for/each/day")
    public JobDtos countJobsInEachDay() {
        return jobService.countJobsInEachDay();
    }
}
