package com.example.job_management_system.service;
import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobService {

     List<JobDto> getAllJobs();

     JobDto createJob(JobDto jobDto);

     JobDto findJobById(Long id);

     void deleteJob(Long id);

     JobDto updateJob(Long id, JobDto jobDto);

     List<JobDto> searchJobByParam(String query);

     PageResponse<JobDto> getAllJobsWithPagiantion(int page, int size, String sortBy);
}
