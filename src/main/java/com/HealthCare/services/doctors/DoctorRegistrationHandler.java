package com.HealthCare.services.doctors;

import com.HealthCare.dto.DoctorRegistrationDto;
import com.HealthCare.entities.Doctor;
import com.HealthCare.exceptions.AlreadyExistsException;
import com.HealthCare.exceptions.UnauthorizedException;
import com.HealthCare.repositories.implementations.DoctorRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
public class DoctorRegistrationHandler {

    private final DoctorRepositoryImplementation doctorRepositoryImplementation;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorRegistrationHandler(DoctorRepositoryImplementation doctorRepositoryImplementation,PasswordEncoder passwordEncoder) {
        this.doctorRepositoryImplementation = doctorRepositoryImplementation;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> handle(DoctorRegistrationDto doctorRegistrationDto) throws AlreadyExistsException {
        if (doctorRepositoryImplementation.existsByCpfOrEmail(doctorRegistrationDto.getEmail())) {
            throw new AlreadyExistsException("Esse email já foi cadastrado.");
        }

        Doctor doctor = new Doctor(
                doctorRegistrationDto.getFullName(),
                doctorRegistrationDto.getEmail(),
                doctorRegistrationDto.getCrm(),
                doctorRegistrationDto.getCpf(),
                passwordEncoder.encode(doctorRegistrationDto.getPassword()),
                doctorRegistrationDto.getDateBirth(),
                doctorRegistrationDto.getLastLogin(),
                doctorRegistrationDto.getCreatedAt(),
                doctorRegistrationDto.getUpdatedAt(),
                doctorRegistrationDto.isIsActive()
        );

        doctorRepositoryImplementation.save(doctor);

        return new ResponseEntity<>("O registro foi um sucesso.",HttpStatus.OK);
    }
}
