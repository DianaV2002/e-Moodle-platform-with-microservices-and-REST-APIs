package com.example.View;

import com.example.Model.Entities.Profesor;
import com.example.Model.Entities.Student;
import com.example.Model.Entities.Disciplina;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


public class UtilConversions {
    public StudentDTO StudentToDTO(Student student){
        if(student == null)
            return new StudentDTO();
        return new StudentDTO(student.getPrenume(), student.getNume(), student.getEmail(), student.getCiclu_studii(), student.getAn_studiu(), student.getGrupa());
    }
    public ProfesorDTO ProfessorToDTO(Profesor prof){
        if(prof == null)
            return new ProfesorDTO();
        return new ProfesorDTO(prof.getPrenume(), prof.getNume(), prof.getEmail(), prof.getGradDidactic(), prof.getTip_asociere(), prof.getAfiliere());
    }
    public DisciplinaDTO DisciplinaToDTO(Disciplina disciplina){
            if(disciplina == null)
                return new DisciplinaDTO();
            return new DisciplinaDTO(disciplina.getNume_disciplina(), disciplina.getTip_disciplina(), disciplina.getCategorie_disciplina(), disciplina.getTip_examinare());
        }
        // public static Disciplina getDisciplinaFromDTO(DisciplinaDTO disciplinaDTO){
        //     return new Disciplina(disciplinaDTO.getNume_disciplina(), disciplinaDTO.getTip_disciplina(), disciplinaDTO.getCategorie_disciplina(), disciplinaDTO.getTip_examinare());
        // }
    
        // public static Profesor getProfessorFromDTO(ProfesorDTO prof, Disciplina discipline){
        //     return new Profesor(prof.getPrenume(), prof.getNume(), prof.getEmail(), prof.getGrad_didactic(), prof.getTip_asociere(), prof.getAfiliere(), discipline);;
        // }
    
        public static ResponseEntity<?> getDTOFromLectureListWithPagination(@RequestParam Optional<Integer> page, @RequestParam(defaultValue = "10") Optional<Integer> items_per_page, List<Disciplina> discipline, List<DisciplinaDTO> disciplineDTO, Integer nrOfPages) {
            UtilConversions utilConversions = new UtilConversions();
            if(discipline.size() > items_per_page.get()) {
                for (Integer i = 0; i < items_per_page.get(); i++) {
                    try {
                        disciplineDTO.add(utilConversions.DisciplinaToDTO(discipline.get((page.get() - 1) * items_per_page.get() + i)));
                } catch (IndexOutOfBoundsException e) {
                    if(disciplineDTO.isEmpty()){
                        // disicplineDTO parent = linkTo(methodOn(DisciplineController.class).listMusic(Optional.of(Integer.parseInt(page.get().toString()) - 1), items_per_page, Optional.empty(), Optional.empty())).withRel("parent");
                        //TODO aici trebuie modificat! sa dai link la pagina anterioara ca parent
                        return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
                    }
                }
            }
        }
        else {
            for (Disciplina disciplina : discipline) {
                disciplineDTO.add(utilConversions.DisciplinaToDTO(disciplina));
            }
        }
        //List<Link> allSongsLinks = createHATEOAS(songsDto, page, items_per_page, nrOfPages);
        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }
}
