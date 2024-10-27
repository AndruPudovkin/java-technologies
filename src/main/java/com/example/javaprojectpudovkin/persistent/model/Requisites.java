package com.example.javaprojectpudovkin.persistent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requisites", schema = "public", catalog = "usersdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Requisites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "settlement_account", nullable = false)
    private String settlementAccount;

    @Column(name = "bic", nullable = false)
    private String bic;

    @Column(name = "correspondent_account", nullable = false)
    private String correspondentAccount;

    @Column(name = "inn", nullable = false)
    private String inn;

    @Column(name = "kpp", nullable = false)
    private String kpp;

    @Column(name = "kbk", nullable = false)
    private String kbk;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
