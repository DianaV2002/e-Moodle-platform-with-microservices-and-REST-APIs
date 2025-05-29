package com.example.Model.Entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.example.Model.Enums.LectureCategoryEnum;
import com.example.Model.Enums.LectureExamTypeEnum;
import com.example.Model.Enums.LectureTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
@Table(name = "disciplina")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Disciplina {
    @Id
    private String cod;
    private String nume_disciplina;
    private int an_studiu;
    @Enumerated(EnumType.STRING)
    private LectureTypeEnum tip_disciplina;
    @Enumerated(EnumType.STRING)
    private LectureCategoryEnum categorie_disciplina;
    @Enumerated(EnumType.STRING)
    private LectureExamTypeEnum tip_examinare;

    @ManyToOne
    @JoinColumn(name = "id_titular", referencedColumnName = "id")
    @JsonBackReference
    private Profesor profesor;

    @ManyToMany(mappedBy = "discipline")
    @JsonBackReference
    private List<Student> studenti = new ArrayList<>(); 

}
