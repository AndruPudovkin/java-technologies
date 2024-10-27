package com.example.javaprojectpudovkin.persistent.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public", catalog = "usersdb")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "inn", nullable = false)
    private String inn;

    @Column(name = "snils", nullable = false)
    private String snils;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pass", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<UserRole> roles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Requisites requisites;
}
