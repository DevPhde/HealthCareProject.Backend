package com.HealthCare.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@jakarta.persistence.Entity
@Table(name = "Doctor")
public class Doctor extends User implements Serializable {

    @Column(name = "CRM")
    @NotNull
    private String Crm;

    @Column(name = "Date_Birth")
    @NotNull
    private Date DateBirth;

    public Doctor(String fullName, String email, String crm, String cpf, String password, Date dateBirth, Date lastLogin, Date createdAt, Date updatedAt, Boolean isActive) {
        FullName = fullName;
        Email = email;
        Crm = crm;
        Cpf = cpf;
        Password = password;
        DateBirth = dateBirth;
        LastLogin = lastLogin;

        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        IsActive = isActive;
    }

    public Doctor(Integer id ,String fullName, String email, String crm, String cpf, String password, Date dateBirth, Date lastLogin, Date createdAt, Date updatedAt, Boolean isActive) {
        this(fullName, email, crm, cpf, password, dateBirth, lastLogin, createdAt, updatedAt, isActive);
        Id = id;
    }
}
