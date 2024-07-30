package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso){
        var cursoCreated = cursoService.create(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreated);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Curso> update(@PathVariable  Long id, @RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.update(id,curso));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable  Long id){
        cursoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
