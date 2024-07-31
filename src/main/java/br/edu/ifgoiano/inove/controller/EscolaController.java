package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/escolas")
public class EscolaController {
    @Autowired
    private EscolaService schoolService;

    @GetMapping
    public List<Escola> list(){
        return schoolService.list();
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<?> findOne(@PathVariable String escolaId){
        try {
            Escola savedSchool = schoolService.findById(Long.parseLong(escolaId));

            return ResponseEntity.status(HttpStatus.OK).body(savedSchool);
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public Escola create(@RequestBody @Validated  Escola school){
        return schoolService.create(school);
    }

    @PutMapping("/{schoolId}")
    public ResponseEntity<?> update(@PathVariable Long schoolId, @RequestBody Escola school){
        Escola updatedSchool = schoolService.update(schoolId, school);

        return ResponseEntity.status(HttpStatus.OK).body(updatedSchool);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{schoolId}")
    public ResponseEntity<?> delete(@PathVariable String schoolId){
            schoolService.deleteById(Long.parseLong(schoolId));
            return ResponseEntity.noContent().build();
    }
}
