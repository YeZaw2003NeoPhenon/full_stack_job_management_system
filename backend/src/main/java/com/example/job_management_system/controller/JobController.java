package com.example.job_management_system.controller;


import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.response.CustomResponse;
import com.example.job_management_system.response.PageResponse;
import com.example.job_management_system.service.JobServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/all")
    public ResponseEntity<CustomResponse<List<JobDto>>> getAllJobs(@RequestParam(value = "_limit", required = false) Integer limit){
        List<JobDto> jobs = jobService.getAllJobs();

        if(limit != null){
            jobs = jobs.stream().limit(limit).toList();
        }

        CustomResponse<List<JobDto>> response = new CustomResponse.Builder<List<JobDto>>()
                .status(HttpStatus.OK.value())
                .message("Jobs fetched successfully")
                .data(jobs)
                .build();

        logger.info("Jobs: {}", jobs);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<JobDto>> createJob(@RequestBody @Valid JobDto jobDto){
        JobDto createdJob = jobService.createJob(jobDto);

        CustomResponse<JobDto> response = new CustomResponse.Builder<JobDto>()
                .status(HttpStatus.CREATED.value())
                .message("Jobs created successfully")
                .data(createdJob)
                .build();
        logger.info("Job is created successfully by {}", jobDto.getTitle());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<JobDto>> getJobById(@PathVariable Long id){
        JobDto trackedJob = jobService.findJobById(id);

        CustomResponse<JobDto> response = new CustomResponse.Builder<JobDto>()
                .status(HttpStatus.OK.value())
                .message("Job is fetched successfully")
                .data(trackedJob)
                .build();

        logger.info("Job: {}", trackedJob);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<CustomResponse<List<JobDto>>> getJobsByParam(@RequestParam(value = "query") String query){

        List<JobDto> jobs = jobService.searchJobByParam(query);

        CustomResponse<List<JobDto>> response = new CustomResponse.Builder<List<JobDto>>()
                .status(HttpStatus.OK.value())
                .message("Job is fetched successfully")
                .data(jobs)
                .build();

        logger.info("Jobs: {}", jobs);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        logger.info("Job is deleted successfully by {}", id);
        return ResponseEntity.status(HttpStatus.OK).body("job is deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<JobDto>> updateJob(@PathVariable Long id,@Valid @RequestBody JobDto jobDto){
        JobDto updatedJob = jobService.updateJob(id, jobDto);

        CustomResponse<JobDto> response = new CustomResponse.Builder<JobDto>()
                .status(HttpStatus.CREATED.value())
                .message("Jobs Updated successfully")
                .data(updatedJob)
                .build();

        logger.info("Job is updated successfully by {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/with-pagination")
    public ResponseEntity<CustomResponse<Object>> findJobsWithPagination(@RequestParam("page") int page , @RequestParam("size") int size, @RequestParam(name = "sortBy", required = false) String sortBy){

       PageResponse<JobDto> jobPage = jobService.getAllJobsWithPagiantion(page,size,sortBy);

        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("CurrentPage",jobPage.getPage());
        responseMap.put("TotalPages",jobPage.getTotalPages());
        responseMap.put("TotalItems", jobPage.getTotalElements());
        responseMap.put("Jobs", jobPage.getContent());

        CustomResponse<Object> response = new CustomResponse.Builder<Object>()
                .status(HttpStatus.OK.value())
                .message("Page Of Jobs")
                .data(responseMap)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
