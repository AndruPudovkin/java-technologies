package com.example.javaprojectpudovkin.persistent.repository;

import com.example.javaprojectpudovkin.dto.RestDto;
import com.example.javaprojectpudovkin.persistent.model.Requisites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitesRepository extends JpaRepository<Requisites, Integer> {

    @Query(value = """
        SELECT new com.example.javaprojectpudovkin.dto.RestDto(u.firstName, r.settlementAccount, r.kbk)
        FROM Requisites r
        JOIN User u ON r.user.id = u.id
        WHERE u.id =:id""")
    RestDto findRestDtoById(Long id);
}
