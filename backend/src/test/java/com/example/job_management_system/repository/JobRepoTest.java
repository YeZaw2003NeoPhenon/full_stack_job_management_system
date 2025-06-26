package com.example.job_management_system.repository;
import com.example.job_management_system.entity.Company;
import com.example.job_management_system.entity.Job;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JobRepoTest {
    private final JobRepository jobRepository;


    @Autowired
    public JobRepoTest(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Job savedJob1;

    @BeforeEach
    void setUp() {

        Company company = new Company("LoliTech","blash blash...", "test@gmail.com","09999999999");

        Job job1 = new Job("Full Stack Java Developer", "FullStack","Remote","blash blash","50000$", company);
        Job job2 = new Job("React Developer", "Frontend","Remote","blash blash","50000$", company);
        Job job3 = new Job("Java Developer", "Backend","Remote","blash blash","50000$",company);
        Job job4 = new Job("DevOps Engineer", "Deployment","FullTime", "blash blash","50000$", company);

        jobRepository.saveAll(Arrays.asList(job1,job2,job3,job4));

        savedJob1 = jobRepository.findAll().get(0);
    }

    @Test
    @DisplayName("Should Return Precise Job, including company data")
    public void testFindJobWithCompanyId(){
       Optional<Job> optionalJob = jobRepository.findJobWithCompanyById(savedJob1.getId());
       assertThat(optionalJob).isPresent();
       Job job = optionalJob.get();
       assertThat(job.getTitle()).isEqualTo("Full Stack Java Developer");
       assertThat(job.getCompany().getName()).isNotNull();
       assertThat(job.getCompany().getName()).isEqualTo("LoliTech");
    }

    @Test
    @DisplayName("Should Return Jobs By Param")
    public void testFindJobsByParam(){
        List<Job> jobs = jobRepository.findJobByParam("React");
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getTitle().toLowerCase()).contains("react");
        assertThat(jobs.get(0).getDescription().toLowerCase()).contains("blash");
    }

    @Test
    @DisplayName("Should Return Jobs With Pagination")
    public void testFindJobsWithPagination(){
        
        Page<Job> jobPage = jobRepository.findAllJobsWithPagination(
                PageRequest.of(0, 2, Sort.by("title").ascending())
        );

        assertThat(jobPage.getContent()).isNotEmpty();
        assertThat(jobPage.getContent().size()).isEqualTo(2);
        assertThat(jobPage.getNumberOfElements()).isEqualTo(2);
        assertThat(jobPage.getTotalElements()).isEqualTo(4);
        assertThat(jobPage.getTotalPages()).isEqualTo(2);
        assertThat(jobPage.getContent().get(0).getTitle()).isEqualTo("DevOps Engineer");
        assertThat(jobPage.getContent().get(1).getTitle()).isEqualTo("Full Stack Java Developer");
    }

    @AfterEach
    void TearDown(){
        jobRepository.deleteAll();
    }

}
