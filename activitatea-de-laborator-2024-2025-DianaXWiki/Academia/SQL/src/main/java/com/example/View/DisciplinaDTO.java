package com.example.View;

import lombok.*;

import org.springframework.hateoas.RepresentationModel;

import com.example.Model.Enums.LectureCategoryEnum;
import com.example.Model.Enums.LectureExamTypeEnum;
import com.example.Model.Enums.LectureTypeEnum;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaDTO extends RepresentationModel{
    private String nume_disciplina;
    @Enumerated(EnumType.STRING)
    private LectureTypeEnum tip_disciplina;
    @Enumerated(EnumType.STRING)
    private LectureCategoryEnum categorie_disciplina;
    @Enumerated(EnumType.STRING)
    private LectureExamTypeEnum tip_examinare;
}
