package com.HealthCare.controllers;

import com.HealthCare.dto.DoctorRegistrationDto;
import com.HealthCare.exceptions.AlreadyExistsException;
import com.HealthCare.repositories.DoctorRepository;
import com.HealthCare.services.doctors.DoctorRegistrationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private final DoctorRegistrationHandler doctorRegistrationHandler;

    public DoctorController(DoctorRegistrationHandler doctorRegistrationHandler) {
        this.doctorRegistrationHandler = doctorRegistrationHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<String> doctorRegister(@RequestBody DoctorRegistrationDto doctorRegistrationDto){
        try{
            return doctorRegistrationHandler.handle(doctorRegistrationDto);
        }
        catch (AlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
