package com.HealthCare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;


@MappedSuperclass
@Data
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected Integer Id;

    @Column(name = "Created_At")
    @NotNull
    protected Date CreatedAt;

    @Column(name = "Updated_At")
    @NotNull
    protected Date UpdatedAt;

    @Column(name = "Is_Active")
    @NotNull
    protected boolean IsActive;
}
