package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.SecaoSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Secao;
import br.edu.ifgoiano.inove.domain.service.SecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/cursos/{courseId}/secoes")
public class SecaoController {
    @Autowired
    private SecaoService sectionService;

    @GetMapping
    public List<SecaoSimpleOutputDTO> list(@PathVariable Long courseId){
        return sectionService.list(courseId);
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<?> findOne(@PathVariable Long courseId, @PathVariable Long sectionId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    sectionService.getOne(courseId, sectionId));
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public Secao create(@PathVariable Long courseId, @RequestBody @Validated  Secao section){
        return sectionService.create(courseId, section);
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<?> update(@PathVariable Long courseId, @PathVariable Long sectionId, @RequestBody Secao section){
        Secao updatedSection = sectionService.update(courseId, sectionId, section);

        return ResponseEntity.status(HttpStatus.OK).body(updatedSection);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{sectionId}")
    public ResponseEntity<?> delete(@PathVariable Long courseId,@PathVariable Long sectionId){
            sectionService.deleteById(courseId, sectionId);
            return ResponseEntity.noContent().build();
    }
}
