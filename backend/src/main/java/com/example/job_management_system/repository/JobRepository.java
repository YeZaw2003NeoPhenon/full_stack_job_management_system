package com.example.job_management_system.repository;

import com.example.job_management_system.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // just for practice to try JPQL
    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.id = :id")
    Optional<Job> findJobWithCompanyById(@Param("id") Long id);

    @Query("SELECT j FROM Job j " +
            "WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(j.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Job> findJobByParam(@Param("query") String query);

    @Query(value = "SELECT j FROM Job j JOIN FETCH j.company",
           countQuery = "SELECT COUNT(j) FROM Job j")
    Page<Job> findAllJobsWithPagination(Pageable pageable);

}
