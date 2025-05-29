package com.example.Model.Services;

import com.example.Model.Repositories.ProfessorRepository;
import com.example.Model.Entities.Profesor;
import com.example.Model.Enums.ProfessorAcademicRank;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Profesor> findAll() {
        return professorRepository.findAll();
    }
    
    public List<Profesor> findByGradDidactic(ProfessorAcademicRank rank){
        return professorRepository.findByGradDidactic(rank);
    }

    public List<Profesor> findByNameStartsWith(String nume){
        return professorRepository.findByNameStartsWith(nume);
    }
    
    public Optional<Profesor> findById(int id) {
        return professorRepository.findById(id);
    }

    public Profesor save(Profesor professor) {
        return professorRepository.save(professor);
    }

    public void deleteById(int id) {
        professorRepository.deleteById(id);
    }
}
