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
                        "SELECT * FROM Doctor WHERE CPF = :cpfOrEmail OR Email = :cpfOrEmail", Doctor.class)
                .setParameter("cpfOrEmail", cpfOrEmail)
                .getResultList();

        return results.isEmpty() ? null : (Doctor) results.get(0);
    }

    public boolean existsByCpfOrEmail(String cpfOrEmail){
        List results = entityManager.createNativeQuery(
                        "SELECT * FROM Doctor WHERE CPF = :cpfOrEmail OR Email = :cpfOrEmail", Doctor.class)
                .setParameter("cpfOrEmail", cpfOrEmail)
                .getResultList();

        return results.isEmpty() ? true : false;
    }

    public void save(Doctor doctor) {
        entityManager.getTransaction().begin();
        entityManager.persist(doctor);
        entityManager.getTransaction().commit();
    }
}