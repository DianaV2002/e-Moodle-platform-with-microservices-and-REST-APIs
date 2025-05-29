package com.example.View;

import lombok.*;

import org.springframework.hateoas.RepresentationModel;

import com.example.Model.Enums.ProfessorAcademicRank;
import com.example.Model.Enums.ProfessorAssociationTypeEnum;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO extends RepresentationModel{
    private String prenume;
    private String nume;
    private String email;
    @Enumerated(EnumType.STRING)
    private ProfessorAcademicRank grad_didactic;
    @Enumerated(EnumType.STRING)
    private ProfessorAssociationTypeEnum tip_asociere;
    private String afiliere;
}
