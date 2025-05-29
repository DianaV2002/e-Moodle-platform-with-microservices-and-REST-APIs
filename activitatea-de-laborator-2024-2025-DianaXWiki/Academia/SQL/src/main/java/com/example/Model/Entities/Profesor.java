package com.example.Model.Entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.example.Model.Enums.ProfessorAcademicRank;
import com.example.Model.Enums.ProfessorAssociationTypeEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prenume;
    private String nume;
    private String email;
    @Column(name = "grad_didactic")
    @Enumerated(EnumType.STRING)
    private ProfessorAcademicRank gradDidactic;
    @Enumerated(EnumType.STRING)
    private ProfessorAssociationTypeEnum tip_asociere;
    private String afiliere;
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Disciplina> discipline = new ArrayList<>();
}


