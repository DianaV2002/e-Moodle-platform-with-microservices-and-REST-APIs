package com.example.View;

import org.springframework.hateoas.RepresentationModel;

import com.example.Model.Enums.StudyProgramEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends RepresentationModel{
    private String prenume;
    private String nume;
    private String email;
    private StudyProgramEnum ciclu_studii;
    private String grupa;
    private int an_studiu;
}