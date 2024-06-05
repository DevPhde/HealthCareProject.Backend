package com.HealthCare.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public abstract class User extends Entity {
    @Column(name = "Full_Name")
    @NotNull
    protected String FullName;
    @Column(name = "Email")
    @NotNull
    protected String Email;
    @Column(name = "CPF")
    @NotNull
    protected String Cpf;
    @Column(name = "Password")
    @NotNull
    protected String Password;
    @Column(name = "Last_Login")
    @NotNull
    protected Date LastLogin;
}
