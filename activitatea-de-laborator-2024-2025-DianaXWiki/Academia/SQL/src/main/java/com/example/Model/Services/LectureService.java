package com.example.Model.Services;
import com.example.Model.Entities.Disciplina;
import com.example.Model.Repositories.LectureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    public List<Disciplina> findAll() {
        return lectureRepository.findAll();
    }

    public Optional<Disciplina> findByCod(String code) {
        return lectureRepository.findByCod(code);
    }


    public void saveLecture(Disciplina lecture) {
        lectureRepository.save(lecture);
    }

    public Disciplina getLecture(String code) {
        return lectureRepository.findByCod(code).get();
    }

    public void deleteByCode(String code) {
       lectureRepository.deleteByCod(code);
    }

}