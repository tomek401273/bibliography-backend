package com.tgrajkowski.model.job;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cloud-repository")
public interface JobDaoProxy {
    @GetMapping(value = "/job/data/monthly/chart")
    List<JobDaily> getJobsForEachDay();

    @PostMapping(value = "job/new/job")
    JobDto saveNewJob(@RequestBody JobDto jobDto);
}
