package com.HealthCare.services.users;

import com.HealthCare.entities.Entity;
import com.HealthCare.entities.Patient;
import com.HealthCare.entities.User;
import com.HealthCare.enums.UserTypeEnum;
import com.HealthCare.exceptions.BadRequestException;
import com.HealthCare.exceptions.UnauthorizedException;
import com.HealthCare.inputModels.UserAuthorizationInputModel;
import com.HealthCare.providers.jwt.JwtProvider;
import com.HealthCare.repositories.DoctorRepository;
import com.HealthCare.repositories.PatientRepository;
import com.HealthCare.repositories.implementations.DoctorRepositoryImplementation;
import com.HealthCare.repositories.implementations.PatientRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthorizationHandler {
      @Autowired
    private final DoctorRepositoryImplementation doctorRepository;
    @Autowired
    private final PatientRepositoryImplementation patientRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtProvider jwtProvider;

    public UserAuthorizationHandler(DoctorRepositoryImplementation doctorRepository, PatientRepositoryImplementation patientRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public Map<String, String> handle(UserAuthorizationInputModel authorizeModel) throws UnauthorizedException {
        User user = verifyCredentials(authorizeModel, true);
        if (user != null){
            return createResponse(user, UserTypeEnum.Doctor);
        }

        user = verifyCredentials(authorizeModel, false);
        if (user != null) {
            return createResponse(user, UserTypeEnum.Patient);
        }

        throw new UnauthorizedException("Credenciais Inválidas.");

    }
    private User verifyCredentials(UserAuthorizationInputModel authModel, boolean isDoctor) throws UnauthorizedException {
        Optional<User> optionalUser = isDoctor ? Optional.ofNullable(doctorRepository.findByCpfOrEmail(authModel.User)) :
                Optional.ofNullable(patientRepository.findByCpfOrEmail(authModel.User));
        User user = optionalUser.orElse(null);

        if (user != null && !passwordEncoder.matches(authModel.Password, user.getPassword())) {
            return null;
        }

        return user;
    }

    private Map<String, String> createResponse(User user, UserTypeEnum userType) {
        String hashJwt = jwtProvider.createToken(user.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("hash", hashJwt);
        response.put("userType", userType.toString());
        return response;
    }
}
