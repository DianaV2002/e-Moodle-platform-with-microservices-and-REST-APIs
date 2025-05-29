package com.example.Model.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Entities.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
