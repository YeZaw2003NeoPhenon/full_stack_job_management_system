package com.example.job_management_system.service;

import com.example.job_management_system.dto.CompanyDto;
import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.entity.Company;
import com.example.job_management_system.entity.Job;
import com.example.job_management_system.exception.CompanyNotFoundException;
import com.example.job_management_system.exception.JobNotFoundException;
import com.example.job_management_system.mapper.EntityConverter;
import com.example.job_management_system.repository.CompanyRepository;
import com.example.job_management_system.repository.JobRepository;
import com.example.job_management_system.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;

    private final EntityConverter<Job,JobDto> jobEntityConverter;

    private final EntityConverter<Company,CompanyDto> companyEntityConverter;

    private final CompanyRepository companyRepository;

    @Autowired
    public JobServiceImp(JobRepository jobRepository,
                         EntityConverter<Job, JobDto> jobEntityConverter, EntityConverter<Company, CompanyDto> companyEntityConverter,
                         CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.jobEntityConverter = jobEntityConverter;
        this.companyEntityConverter = companyEntityConverter;
        this.companyRepository = companyRepository;
    }

    @Override
    @Cacheable(value = "getAllJobs")
    public List<JobDto> getAllJobs() {

       return jobRepository.findAll()
                                             .stream()
                                             .map(job -> {
                                            JobDto jobDto = jobEntityConverter.entityToDto(job, JobDto.class);
                                                 Company company = job.getCompany();
                                                 // to either include the company datas
                                                 if(company != null){
                                                     CompanyDto companyDto = companyEntityConverter.entityToDto(company, CompanyDto.class);
                                                     jobDto.setCompanyDto(companyDto);
                                                 }
                                                 return jobDto;
                                             })
                                             .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = {"getAllJobs","getJobById"}, allEntries = true)
    public JobDto createJob(JobDto jobDto) {
        CompanyDto companyDto = jobDto.getCompanyDto();

        if (companyDto == null) {
            throw new IllegalArgumentException("Company info is required");
        }

        // check if the company already exists, then no need to create new company
        Company company;

        if(companyDto.getId() != null ){
            company = companyRepository.findById(companyDto.getId()).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        }
        else{
            company = companyEntityConverter.dtoToEntity(companyDto, Company.class);
            company = companyRepository.save(company);
        }

       Job job = jobEntityConverter.dtoToEntity(jobDto, Job.class);

       job.setCompany(company);

       Job createdJob = jobRepository.save(job);

       return jobEntityConverter.entityToDto(createdJob, JobDto.class);
    }

    @Override
    @Cacheable(value = "getJobById",  key = "#id")
    public JobDto findJobById(Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    JobDto jobDto = jobEntityConverter.entityToDto(job, JobDto.class);
                     Company company = job.getCompany();

                   if(company != null){
                       CompanyDto companyDto = companyEntityConverter.entityToDto(company, CompanyDto.class);
                       jobDto.setCompanyDto(companyDto);
                   }
                   return jobDto;
                })
                .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }

    @Override
    @CacheEvict(value = {"getAllJobs","getJobById"}, key = "#id", allEntries = true)
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        Company company = job.getCompany();
        jobRepository.delete(job);
        if(company != null && company.getId() != null){
            companyRepository.deleteById(company.getId());
        }
    }

    @Override
    @CacheEvict(value = {"getAllJobs","getJobById"}, key = "#id", allEntries = true)
    public JobDto updateJob(Long id, JobDto jobDto) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found"));
        job.setTitle(jobDto.getTitle());
        job.setType(jobDto.getType());
        job.setLocation(jobDto.getLocation());
        job.setDescription(jobDto.getDescription());
        job.setSalary(jobDto.getSalary());

        CompanyDto companyDto = jobDto.getCompanyDto();

        if(companyDto != null){

            Company company = job.getCompany();

            // if the job had previously no company
            if(company == null){
                company = companyEntityConverter.dtoToEntity(companyDto, Company.class);
            }
            else{
                company.setName(companyDto.getName());
                company.setDescription(companyDto.getDescription());
                company.setContactEmail(companyDto.getContactEmail());
                company.setContactPhone(companyDto.getContactPhone());
            }
            Company savedCompany = companyRepository.save(company);
            job.setCompany(savedCompany);
        }

        Job savedJob = jobRepository.save(job);
        return jobEntityConverter.entityToDto(savedJob,JobDto.class);
    }

    @Override
    @Cacheable(value = "getAllJobs")
    public List<JobDto> searchJobByParam(String query) {

        return jobRepository.findJobByParam(query).stream()
                .map(job -> {
                    JobDto jobDto = jobEntityConverter.entityToDto(job, JobDto.class);
                    Company company = job.getCompany();

                    if(company != null){
                        CompanyDto companyDto = companyEntityConverter.entityToDto(company, CompanyDto.class);
                        jobDto.setCompanyDto(companyDto);
                    }
                    return jobDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    // @Cacheable(value = "getAllJobs")
    @Cacheable(value = "getAllJobs", key = "#page + '-' + #size + '-' + #sortBy")
    public PageResponse<JobDto> getAllJobsWithPagiantion(int pageNum, int size, String sortBy) {

       Pageable pageable = PageRequest.of(pageNum,size, Sort.by(sortBy).ascending());
       Page<Job> jobPage = jobRepository.findAllJobsWithPagination(pageable);

       List<JobDto> jobDtoList = jobPage.getContent().stream()
               .map(job -> {
                   JobDto jobDto = jobEntityConverter.entityToDto(job, JobDto.class);
                   Company company = job.getCompany();
                  if(company != null ) {
                      CompanyDto companyDto = companyEntityConverter.entityToDto(company, CompanyDto.class);
                      jobDto.setCompanyDto(companyDto);
                  }
                  else{
                      CompanyDto companyDto = null;
                      jobDto.setCompanyDto(null);
                  }
                  return jobDto;
               })
               .collect(Collectors.toList());

        Page<JobDto> page = new PageImpl<>(jobDtoList,pageable,jobPage.getTotalElements());
       return new PageResponse<>(page);
    }

}
