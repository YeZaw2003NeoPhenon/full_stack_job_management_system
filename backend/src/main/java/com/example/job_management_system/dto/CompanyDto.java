package com.example.job_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CompanyDto {

    private Long id;

    @NotBlank(message = "name must not be null")
    private String name;

    @NotBlank(message = "description must not be null")
    private String description;

    @NotBlank(message = "contactEmail must not be null")
    @Email(message = "invalid email")
    private String contactEmail;

    @NotBlank(message = "contactPhone must not be null")
    private String contactPhone;

    public CompanyDto(){}


    public CompanyDto(Long id, String name, String description, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public CompanyDto(String name, String description, String contactEmail, String contactPhone) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
