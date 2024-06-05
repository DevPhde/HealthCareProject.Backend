package com.HealthCare.repositories.implementations;

import com.HealthCare.entities.Doctor;
import com.HealthCare.entities.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepositoryImplementation {

    @PersistenceContext
    private EntityManager entityManager;

    public Patient findByCpfOrEmail(String cpfOrEmail) {
        List results = entityManager.createNativeQuery(
                        "SELECT * FROM Patient WHERE CPF = :cpfOrEmail OR Email = :cpfOrEmail", Patient.class)
                .setParameter("cpfOrEmail", cpfOrEmail)
                .getResultList();

        return results.isEmpty() ? null : (Patient) results.get(0);
    }
}