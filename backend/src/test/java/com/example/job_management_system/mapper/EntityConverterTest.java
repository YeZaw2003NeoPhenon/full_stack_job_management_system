package com.example.job_management_system.mapper;


import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.entity.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;


class EntityConverterTest {

    private ModelMapper modelMapper = new ModelMapper();
    private EntityConverter<Job, JobDto> entityConverter;

    @BeforeEach
    void setUp(){
        this.entityConverter = new EntityConverter<>(modelMapper);
    }

    @Test
    public void testEntityToDto() {

        Job job = new Job();
        job.setId(1L);
        job.setTitle("Software Engineer");
        job.setDescription("Develop software applications");
        job.setType("Full-time");
        job.setLocation("New York");
        job.setSalary("100000");

        JobDto jobDto = entityConverter.entityToDto(job, JobDto.class);

        assertThat(jobDto).isNotNull();
        assertThat(jobDto.getTitle()).isEqualTo(job.getTitle());
        assertThat(jobDto.getDescription()).isEqualTo(job.getDescription());
        assertThat(jobDto.getType()).isEqualTo(job.getType());
        assertThat(jobDto.getLocation()).isEqualTo(job.getLocation());
        assertThat(jobDto.getSalary()).isEqualTo(job.getSalary());
    }

   @Test
    public void testDtoToEntity() {

       JobDto jobDto = new JobDto();
       jobDto.setId(1L);
       jobDto.setTitle("Software Engineer");
       jobDto.setDescription("Develop software applications");
       jobDto.setType("Full-time");
       jobDto.setLocation("New York");
       jobDto.setSalary("100000");

        Job job = entityConverter.dtoToEntity(jobDto, Job.class);

        assertThat(jobDto).isNotNull();
        assertThat(jobDto.getTitle()).isEqualTo(job.getTitle());
        assertThat(jobDto.getDescription()).isEqualTo(job.getDescription());
        assertThat(jobDto.getType()).isEqualTo(job.getType());
        assertThat(jobDto.getLocation()).isEqualTo(job.getLocation());
        assertThat(jobDto.getSalary()).isEqualTo(job.getSalary());
    }

}