package com.example.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import com.example.Model.Enums.StudyProgramEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prenume;
    private String nume;
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyProgramEnum ciclu_studii;
    private String an_studiu;
    private int grupa;

    @ManyToMany
    @JoinTable(
        name = "join_ds",
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "cod_disciplina")
    )
    @JsonManagedReference
    private List<Disciplina> discipline = new ArrayList<>();
}
