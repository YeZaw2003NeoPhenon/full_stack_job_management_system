package com.example.job_management_system.service;
import com.example.job_management_system.dto.JobDto;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobService {

     List<JobDto> getAllJobs();

     JobDto createJob(JobDto jobDto);

     JobDto findJobById(Long id);

     void deleteJob(Long id);

     JobDto updateJob(Long id, JobDto jobDto);

     List<JobDto> searchJobByParam(String query);
}
