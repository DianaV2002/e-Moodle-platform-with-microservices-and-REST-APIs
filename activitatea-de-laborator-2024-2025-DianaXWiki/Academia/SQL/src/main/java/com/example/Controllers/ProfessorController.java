package com.example.Controllers;

import com.example.Model.Services.ProfessorService;
import com.example.View.DisciplinaDTO;
import com.example.View.ProfesorDTO;
import com.example.View.UtilConversions;
import com.example.Model.Entities.Disciplina;
import com.example.Model.Entities.Profesor;
import com.example.Model.Entities.Student;
import com.example.Model.Enums.ProfessorAcademicRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/academia/profesori")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;
    private final UtilConversions utilConversions = new UtilConversions();

    @GetMapping("")
    public ResponseEntity<?> getAllProfessors(
            @RequestParam(required = false) String acad_rank,
            @RequestParam(required = false) String name)
  {

        List<ProfesorDTO> profesorsDTO = new ArrayList<>();
        List<Profesor> profesors = new ArrayList<>();

        if (acad_rank != null && !acad_rank.isEmpty()) {
            try {
                ProfessorAcademicRank rank = ProfessorAcademicRank.valueOf(acad_rank.toUpperCase());
                profesors = professorService.findByGradDidactic(rank);
                if (profesors.isEmpty()) {
                    return new ResponseEntity<>("Niciun profesor cu gradul didactic: " + acad_rank, HttpStatus.NOT_FOUND);
                }
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Grad didactic invalid", HttpStatus.BAD_REQUEST);
            }
        }
        
        if (name != null && !name.isEmpty()) {
            profesors = professorService.findByNameStartsWith(name);
            if (profesors.isEmpty()) {
                return new ResponseEntity<>("Niciun profesor cu numele sau prenumele specificate", HttpStatus.NOT_FOUND);
            }
        }
    
        if (acad_rank == null && (name == null || name.isEmpty())) {
            profesors = professorService.findAll();
            if (profesors.isEmpty()) {
                return new ResponseEntity<>(List.of(),
                            HttpStatus.OK);
            }
        }

        for (Profesor profesor : profesors) {
            ProfesorDTO profesorDTO = utilConversions.ProfessorToDTO(profesor);
            profesorsDTO.add(profesorDTO);
        }

        return new ResponseEntity<>(profesorsDTO, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable int id) {
        Profesor prof = null;
        try{
            prof = professorService.findById(id).orElseThrow();
        }catch(Exception e){
            return new ResponseEntity<>("Profesorul nu exista",HttpStatus.NOT_FOUND);
        }
        ProfesorDTO profesorDTO = utilConversions.ProfessorToDTO(prof);
        return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/discipline")
    public ResponseEntity<?> getLecturesForProfessor(@PathVariable int id) {
        Profesor prof = null;

        try{
            prof = professorService.findById(id).orElseThrow();
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profesorul nu exista", HttpStatus.NOT_FOUND);
        }

        if(prof.getDiscipline().size() == 0)
        {
            return new ResponseEntity<>("Profesorul nu este asignat la nicio disciplina", HttpStatus.OK);
        }

        List<DisciplinaDTO> disciplineDTO = new ArrayList<DisciplinaDTO>();
        for(Disciplina disciplina : prof.getDiscipline())
        {
            DisciplinaDTO disciplinaDTO = utilConversions.DisciplinaToDTO(disciplina);
            //Link selfLink = linkTo(ProfessorController.class).slash(prof.getId()).withSelfRel();
            //Link parentLink = linkTo(methodOn(ProfessorController.class).getAllPro()).withRel("parent");
            // songDTO.add(selfLink);
            // songDTO.add(parentLink);
            disciplineDTO.add(disciplinaDTO);
        }

        // Link parentLink = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withRel("parent");
        // Link selfLink = linkTo(methodOn(ArtistController.class).getAllSongsByArtistId(artist_id)).withSelfRel();
        // CollectionModel<SongDTO> result = CollectionModel.of(songsDTO, selfLink);
        // result.add(parentLink);

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }

   
    @PostMapping
    public Profesor createProfessor(@RequestBody Profesor professor) {
        return professorService.save(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable int id) {
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
