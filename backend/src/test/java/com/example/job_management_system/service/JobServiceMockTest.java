package com.example.job_management_system.service;

import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.entity.Company;
import com.example.job_management_system.entity.Job;
import com.example.job_management_system.exception.JobNotFoundException;
import com.example.job_management_system.mapper.EntityConverter;
import com.example.job_management_system.repository.JobRepository;
import com.example.job_management_system.response.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceMockTest {

    // mock instances
    @Mock
    private JobRepository jobRepository;

    @Mock
    private EntityConverter<Job,JobDto> jobEntityConverter;

    @InjectMocks
    private JobServiceImp jobServiceImp; // service instance for under the test

    private Job job;
    private JobDto jobDto;
    private Company company;

    @BeforeEach
    void setUp(){
         company = new Company(1L,"LoliTech","blash blash...", "test@gmail.com","09999999999");
         job = new Job(1L,"Full Stack Java Developer", "FullStack","Remote","blash blash","50000$", company);

        jobDto = new JobDto(job.getId(), job.getTitle(), job.getType(), job.getLocation(), job.getDescription(), job.getSalary(),null);
    }

    @Test
    void testGetAllJobs(){
        //initialize the mock behavior
        when(jobEntityConverter.entityToDto(any(Job.class), eq(JobDto.class))).thenReturn(jobDto);

        when(jobRepository.findAll()).thenReturn(Collections.singletonList(job));
        List<JobDto> jobs = jobServiceImp.getAllJobs();

        assertThat(jobs.get(0).getTitle()).isEqualTo("Full Stack Java Developer");
        assertThat(jobs.get(0).getType()).isEqualTo("FullStack");
        assertThat(jobs.get(0).getLocation()).isEqualTo("Remote");
        assertThat(jobs.get(0).getSalary()).isEqualTo("50000$");
        assertThat(jobs.size()).isEqualTo(1);

        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void testFindJobById() {

        // prepare test mock behavior
        when(jobEntityConverter.entityToDto(any(Job.class), eq(JobDto.class))).thenReturn(jobDto);
        when(jobRepository.findJobWithCompanyById(1L)).thenReturn(Optional.of(job));

        JobDto searchedJob = jobServiceImp.findJobById(1L);
        assertThat(searchedJob).isNotNull();
        assertThat(searchedJob.getTitle()).isEqualTo("Full Stack Java Developer");
        assertThat(searchedJob.getType()).isEqualTo("FullStack");
        verify(jobRepository, times(1)).findJobWithCompanyById(1L);
    }

    @Test
    void testFindJobByParam(){
        when(jobEntityConverter.entityToDto(any(Job.class), eq(JobDto.class))).thenReturn(jobDto);
        when(jobRepository.findJobByParam("Java Developer")).thenReturn(Collections.singletonList(job));
        List<JobDto> jobDtos = jobServiceImp.searchJobByParam("Java Developer");

        assertThat(jobDtos.get(0).getTitle()).isEqualTo("Full Stack Java Developer");

        verify(jobRepository, times(1)).findJobByParam("Java Developer");
    }

    @Test
    void testGetAllJobsWithPagiantion(){
        when(jobEntityConverter.entityToDto(any(Job.class), eq(JobDto.class))).thenReturn(jobDto);

        Page<Job> page = new PageImpl<>(Collections.singletonList(job));

        Pageable pageable = PageRequest.of(0 , 1 , Sort.by("title").ascending());
        when(jobRepository.findAllJobsWithPagination(pageable)).thenReturn(page);

        PageResponse<JobDto> jobDtos = jobServiceImp.getAllJobsWithPagiantion(0, 1, "title");

        assertThat(jobDtos.getContent().size()).isEqualTo(1);
        assertThat(jobDtos.getContent().get(0).getTitle()).isEqualTo("Full Stack Java Developer");
        assertThat(jobDtos.getContent().get(0).getType()).isEqualTo("FullStack");
        assertThat(jobDtos.getContent().size()).isEqualTo(1);
        assertThat(jobDtos.getTotalElements()).isEqualTo(1);

        verify(jobRepository, times(1)).findAllJobsWithPagination(pageable);
    }

    @Test
     void testJobNotFoundException(){

     when(jobRepository.findJobWithCompanyById(100L)).thenReturn(Optional.empty());

     JobNotFoundException exception = assertThrowsExactly(JobNotFoundException.class , () -> {
                                         jobServiceImp.findJobById(100L);
     });

      assertThat(exception.getMessage()).isEqualTo("Job not found");
      verify(jobRepository, times(1)).findJobWithCompanyById(100L);
    }

}
