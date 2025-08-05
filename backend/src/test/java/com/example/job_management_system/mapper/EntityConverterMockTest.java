package com.example.job_management_system.mapper;

import com.example.job_management_system.dto.JobDto;
import com.example.job_management_system.entity.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntityConverterMockTest {

    @Mock
    private ModelMapper modelMapper;

    private EntityConverter<Job, JobDto> entityConverter;

    @BeforeEach
    void setUp() {
        entityConverter = new EntityConverter<>(modelMapper);
    }

    @Test
     void shouldConvertEntityToDto() {
        Job job = new Job();
        job.setId(1L);
        job.setTitle("Software Engineer");
        job.setType("Full-time");
        job.setLocation("New York");

        JobDto jobDto = new JobDto();

        when(modelMapper.map(job, JobDto.class)).thenReturn(jobDto);

        JobDto result = entityConverter.entityToDto(job, JobDto.class);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(jobDto);
        assertThat(result.getId()).isEqualTo(jobDto.getId());
        assertThat(result.getTitle()).isEqualTo(jobDto.getTitle());
        assertThat(result.getType()).isEqualTo(jobDto.getType());

        verify(modelMapper).map(job, JobDto.class);
    }

    @Test
    public void shouldConvertDtoToEntity() {
        JobDto jobDto = new JobDto();
        jobDto.setId(1L);
        jobDto.setTitle("Software Engineer");
        jobDto.setType("Full-time");
        jobDto.setLocation("New York");

        Job job = new Job();
        when(modelMapper.map(jobDto, Job.class)).thenReturn(job);
        Job result = entityConverter.dtoToEntity(jobDto, Job.class);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(job);
        assertThat(result.getId()).isEqualTo(job.getId());
        assertThat(result.getTitle()).isEqualTo(job.getTitle());
        assertThat(result.getType()).isEqualTo(job.getType());
    }
}