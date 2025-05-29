package com.example.Model.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Model.Entities.Profesor;
import com.example.Model.Enums.ProfessorAcademicRank;


@Repository
public interface ProfessorRepository extends JpaRepository<Profesor, Integer> {

    @Query("SELECT p FROM Profesor p WHERE LOWER(p.nume) LIKE LOWER(CONCAT(:name, '%')) OR LOWER(p.prenume) LIKE LOWER(CONCAT(:name, '%'))")
    List<Profesor> findByNameStartsWith(@Param("name") String name);
    List<Profesor> findByGradDidactic(ProfessorAcademicRank rank);
}
