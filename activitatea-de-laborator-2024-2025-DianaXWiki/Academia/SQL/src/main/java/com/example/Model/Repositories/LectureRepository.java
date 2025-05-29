package com.example.Model.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Entities.Disciplina;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Disciplina, String> {
    List<Disciplina> findAll();
    Optional<Disciplina> findByCod(String code);
    void deleteByCod(String code);
}
