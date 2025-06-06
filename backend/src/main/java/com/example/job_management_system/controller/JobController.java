package com.example.job_management_system.controller;


import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.service.JobServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobServiceImp jobService;

    private final Logger logger = LoggerFactory.getLogger(JobController.class);
    @Autowired
    public JobController(JobServiceImp jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobDto>> getAllJobs(@RequestParam(value = "_limit", required = false) Integer limit){
        List<JobDto> jobs = jobService.getAllJobs();
        if(limit != null){
            return ResponseEntity.ok(jobs.stream().limit(limit).toList());
        }
        logger.info("Jobs: {}", jobs);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/create")
    public ResponseEntity<JobDto> createJob(@RequestBody @Valid JobDto jobDto){
        JobDto createdJob = jobService.createJob(jobDto);
        logger.info("Job is created successfully by {}", jobDto.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id){
        JobDto trackedJob = jobService.findJobById(id);
        logger.info("Job: {}", trackedJob);
        return ResponseEntity.ok(trackedJob);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        logger.info("Job is deleted successfully by {}", id);
        return ResponseEntity.status(HttpStatus.OK).body("job is deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id,@Valid @RequestBody JobDto jobDto){
        JobDto updatedJob = jobService.updateJob(id, jobDto);
        logger.info("Job is updated successfully by {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedJob);
    }

}
