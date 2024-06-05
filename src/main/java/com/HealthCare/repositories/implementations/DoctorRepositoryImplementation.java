package com.HealthCare.repositories.implementations;

import com.HealthCare.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepositoryImplementation {

    @PersistenceContext
    private EntityManager entityManager;

    public Doctor findByCpfOrEmail(String cpfOrEmail) {
        List results = entityManager.createNativeQuery(
                        "SELECT * FROM Doctor WHERE u.CPF = :cpfOrEmail OR u.Email = :cpfOrEmail", Doctor.class)
                .setParameter("cpfOrEmail", cpfOrEmail)
                .getResultList();

        return results.isEmpty() ? null : (Doctor) results.get(0);
    }
}