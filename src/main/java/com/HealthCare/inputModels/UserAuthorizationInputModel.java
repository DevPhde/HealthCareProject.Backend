package com.HealthCare.inputModels;

import jakarta.validation.constraints.NotBlank;

public class UserAuthorizationInputModel {
    public UserAuthorizationInputModel(String user, String password) {
        User = user;
        Password = password;
    }

    public UserAuthorizationInputModel() {}

    @NotBlank
    public String User;

    @NotBlank
    public String Password;
}
