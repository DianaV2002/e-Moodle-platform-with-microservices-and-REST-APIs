package com.example.Controllers;

import com.example.Model.Services.LectureService;
import com.example.View.DisciplinaDTO;
import com.example.View.UtilConversions;
import com.example.Model.Entities.Disciplina;
import static com.example.View.UtilConversions.getDTOFromLectureListWithPagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academia/discipline")
public class LectureController {
    @Autowired
    private LectureService lectureService;
    private final UtilConversions utilConversions = new UtilConversions();

    @GetMapping("")
    public ResponseEntity<?> getAllLectures(
            @RequestParam(required = false, defaultValue="0") String page, 
            @RequestParam(required = false, defaultValue="10") String items_per_page
    ) {
        int pageNumber, pageSize;
        try {
            if (page != null && items_per_page != null) {
                pageNumber = Integer.parseInt(page);
                pageSize = Integer.parseUnsignedInt(items_per_page);

                if (pageSize <= 0) {
                    return ResponseEntity.badRequest().body("Parametrul items_per_page trebuie sa fie un numar pozitiv.");
                }

                long totalEntries = lectureService.findAll().size();
                if ((long) pageNumber * pageSize >= totalEntries) {
                    return ResponseEntity.badRequest().body("Combinata dintre page si items_per_page > numarul total itemi disponibili.");
                }
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Parametrii page si items_per_page trebuie să fie numere întregi pozitive.");
        }

        List<Disciplina> lectures = lectureService.findAll();
        pageNumber = Integer.parseInt(page);
        pageSize = Integer.parseUnsignedInt(items_per_page);


        if (page != null && items_per_page != null) {
            List<DisciplinaDTO> disciplineDTO = new ArrayList<>();
            Integer nrOfPages = (int) Math.ceil((double) lectures.size() / pageSize);
            ResponseEntity<?> response = getDTOFromLectureListWithPagination(
                    Optional.of(pageNumber + 1), 
                    Optional.of(pageSize), 
                    lectures, 
                    disciplineDTO, 
                    nrOfPages
            );
            
            return response;
        }

        if (lectures.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.OK);
        }

        List<DisciplinaDTO> disciplineDTO = lectures.stream()
                .map(utilConversions::DisciplinaToDTO)
                .toList();

        return new ResponseEntity<>(disciplineDTO, HttpStatus.OK);
    }


    @GetMapping("/{cod}")
    public ResponseEntity<?> getLectureByCode(@PathVariable String cod) {
        Disciplina disciplina = null;
        try{
            disciplina = lectureService.findByCod(cod).orElseThrow();
        }catch(Exception e){
            return new ResponseEntity<>("Disciplina nu exista",HttpStatus.NOT_FOUND);
        }
        DisciplinaDTO disciplinaDTO = utilConversions.DisciplinaToDTO(disciplina);
        return new ResponseEntity<>(disciplinaDTO, HttpStatus.OK);
    }
    // @PostMapping
    // public LectureDTO createLecture(@RequestBody LectureDTO lectureDTO) {
    //     return lectureService.saveLecture(lectureDTO);
    // }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteLecture(@PathVariable String code) {
        lectureService.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }
}
