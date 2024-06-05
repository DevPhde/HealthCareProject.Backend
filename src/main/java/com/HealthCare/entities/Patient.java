package com.HealthCare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@jakarta.persistence.Entity
@Table(name = "Patient")
public class Patient extends User {

    @Column(name = "Date_Birth")
    @NotNull
    private Date DateBirth;

    @ManyToOne
    @JoinColumn(name = "Doctor_Id", nullable = false)
    private Doctor DoctorId;

    public Patient(String fullName, String email, String cpf, String password, Date dateBirth, Date lastLogin, Doctor doctor, Date createdAt, Date updatedAt, Boolean isActive) {
        FullName = fullName;
        Email = email;
        Cpf = cpf;
        Password = password;
        DateBirth = dateBirth;
        LastLogin = lastLogin;
        DoctorId = doctor;


        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        IsActive = isActive;
    }

    public Patient(Integer id ,String fullName, String email, String cpf, String password, Date dateBirth, Date lastLogin, Doctor doctor, Date createdAt, Date updatedAt, Boolean isActive) {
        this(fullName, email, cpf, password, dateBirth, lastLogin, doctor, createdAt, updatedAt, isActive);
        Id = id;
    }
}
