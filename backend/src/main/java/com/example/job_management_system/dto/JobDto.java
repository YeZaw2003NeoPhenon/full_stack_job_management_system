package com.example.job_management_system.dto;

public class JobDto {

    private Long id;

    private String title;

    private String type;


    private String location;


    private String description;


    private String salary;

    private CompanyDto companyDto;

    public JobDto(){}

//    public JobDto(String title, String type, String location, String description, String salary, CompanyDto companyDto) {
//        this.title = title;
//        this.type = type;
//        this.location = location;
//        this.description = description;
//        this.salary = salary;
//        this.companyDto = companyDto;
//    }

    public JobDto(Long id , String title, String type, String location, String description, String salary, CompanyDto companyDto) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.companyDto = companyDto;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }

    @Override
    public String toString() {
        return "JobDto{id=" + id + ", title='" + title + "', company=" + companyDto + "}";
    }
}
