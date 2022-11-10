package com.example.jobrecruit.controller;

import com.example.jobrecruit.model.Job;
import com.example.jobrecruit.request.UpsertJobRequest;
import com.example.jobrecruit.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class JobController {
    @Autowired
    JobService jobService;

    //GET: api/v1/jobs : get all jobs list
    @GetMapping("jobs")
    public List<Job> getJobList() {
        return jobService.getJobList();
    }

    //POST: api/v1/jobs : create a new job
    @PostMapping("jobs")
    public Job createJob(@RequestBody UpsertJobRequest request) {
        return jobService.createJob(request);
    }

    //GET: api/v1/jobs/{id} : get a job by id
    @GetMapping("jobs/{id}")
    public Job getJobById(@PathVariable String id) {
        return jobService.getJobById(id);
    }

    //PUT: api/v1/jobs/{id} : update a job
    @PutMapping("jobs/{id}")
    public Job updateJobById(@PathVariable String id, @RequestBody UpsertJobRequest request) {
        return jobService.updateJobById(id, request);
    }

    //DELETE : api/v1/jobs/{id} : delete a job
    @DeleteMapping("jobs/{id}")
    public void deleteJobById(@PathVariable String id) {
        jobService.deleteJobById(id);
    }

    //GET: api/v1/jobs/random : get a random job
    @GetMapping("jobs/random")
    public Job getRandomJob() {
        return  jobService.getJobRandom();
    }

    //GET: api/v1/sort?max_salary=desc
    @GetMapping("sort")
    public List<Job> getSortBySalaryList(@RequestParam int max_salary) {
        return jobService.getSortBySalaryList(max_salary);
    }
}
