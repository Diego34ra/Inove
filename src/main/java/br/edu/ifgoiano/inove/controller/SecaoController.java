package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.model.Secao;
import br.edu.ifgoiano.inove.domain.service.SecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/secoes")
public class SecaoController {
    @Autowired
    private SecaoService sectionService;

    @GetMapping
    public List<Secao> listSection(){
        return sectionService.list();
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<?> findOneSection(@PathVariable String sectionId){
        try {
            Secao savedSection = sectionService.findById(Long.parseLong(sectionId));

            return ResponseEntity.status(HttpStatus.OK).body(savedSection);
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public Secao createSection(@RequestBody @Validated  Secao section){
        return sectionService.create(section);
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<?> updateSection(@PathVariable Long sectionId, @RequestBody Secao section){
        Secao updatedSection = sectionService.update(sectionId, section);

        return ResponseEntity.status(HttpStatus.OK).body(updatedSection);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable String sectionId){
            sectionService.deleteById(Long.parseLong(sectionId));
            return ResponseEntity.noContent().build();
    }
}
