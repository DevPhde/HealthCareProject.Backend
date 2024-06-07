package com.HealthCare.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DoctorRegistrationDto {
    private String FullName;
    private String Email;
    private String Crm;
    private String Cpf;
    private String Password;
    private Date dateBirth;
    private Date LastLogin;
    private Date CreatedAt;
    private Date UpdatedAt;
    private boolean IsActive;
}
