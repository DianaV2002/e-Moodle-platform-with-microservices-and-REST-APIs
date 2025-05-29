package com.example.Controllers;

import com.example.Model.Services.StudentService;
import com.example.View.DisciplinaDTO;
import com.example.View.StudentDTO;
import com.example.View.UtilConversions;
import com.example.Model.Entities.Disciplina;
import com.example.Model.Entities.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/academia/studenti")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private final UtilConversions utilConversions = new UtilConversions();

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentsDTO = new ArrayList<StudentDTO>();
        for(StudentDTO studentDTO : studentService.findAll().stream().map(utilConversions::StudentToDTO).toList()){
            studentsDTO.add(studentDTO);
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = null;
        try{
            student = studentService.findById(id).orElseThrow();
        }catch(Exception e){
            return new ResponseEntity<>("Studentul nu exista",HttpStatus.NOT_FOUND);
        }
        StudentDTO studentDTO = utilConversions.StudentToDTO(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}/discipline")
    public ResponseEntity<?> getLecturesForStudent(@PathVariable int id){
        Student student = null;

        try{
            student = studentService.findById(id).orElseThrow();
        }
        catch (Exception e) {
            return new ResponseEntity<>("Studentul nu exista", HttpStatus.NOT_FOUND);
        }

        if(student.getDiscipline().size() == 0)
        {
            return new ResponseEntity<>("Studentul nu este asignat la nicio disciplina", HttpStatus.OK);
        }

        List<DisciplinaDTO> disciplineDTO = new ArrayList<DisciplinaDTO>();
        for(Disciplina disciplina : student.getDiscipline())
        {
            DisciplinaDTO disciplinaDTO = utilConversions.DisciplinaToDTO(disciplina);
            // Link selfLink = linkTo(SongController.class).slash(song.getId()).withSelfRel();
            // Link parentLink = linkTo(methodOn(SongController.class).getAllSongs()).withRel("parent");
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
    public Student createStudent(Student student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
