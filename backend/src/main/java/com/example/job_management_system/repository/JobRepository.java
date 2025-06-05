package com.example.job_management_system.repository;

import com.example.job_management_system.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // just for practice to try JPQL
    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.id = :id")
    Optional<Job> findJobWithCompanyById(@Param("id") Long id);
}
