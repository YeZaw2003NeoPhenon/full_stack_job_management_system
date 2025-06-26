package com.example.job_management_system.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description",length = 1000, nullable = false)
    private String description;

    @Column(name = "salary", nullable = false)
    private String salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    public Job(Long id, String title, String type, String location, String description, String salary, Company company) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.company = company;
    }

    public Job(String title, String type, String location, String description, String salary, Company company) {
        this.title = title;
        this.type = type;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.company = company;
    }

    public Job(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
