package com.example.Model.Services;

import com.example.Model.Repositories.StudentRepository;

import jakarta.transaction.Transactional;

import com.example.Model.Entities.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {

        List<Student> students  = studentRepository.findAll();
        return students;
    }
    //cum ar trb sa handle here?
    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    public Student save(Student studentDTO) {
        return studentRepository.save(studentDTO);
    }

    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

}
