package com.HealthCare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@jakarta.persistence.Entity
@Table(name = "Patient")
public class Patient extends Entity {

    @Column(name = "Full_Name")
    @NotNull
    private String FullName;

    @Column(name = "Email")
    @NotNull
    private String Email;

    @Column(name = "CPF")
    @NotNull
    private String Cpf;

    @Column(name = "Password")
    @NotNull
    private String Password;

    @Column(name = "Date_Birth")
    @NotNull
    private Date DateBirth;

    @Column(name = "Last_Login")
    @NotNull
    private Date LastLogin;

    @ManyToOne
    @JoinColumn(name = "Doctor_Id", nullable = false)
    private Doctor DoctorId;
}
