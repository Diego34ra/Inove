package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/conteudos")
public class ConteudoController {

    @Autowired
    private ConteudoService conteudoService;

    @PostMapping
    public ResponseEntity<Conteudo> create(@RequestBody Conteudo conteudo){
        var conteudoCreated = conteudoService.create(conteudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(conteudoCreated);
    }

    @GetMapping
    public ResponseEntity<List<Conteudo>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(conteudoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Conteudo> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(conteudoService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Conteudo> update(@PathVariable  Long id, @RequestBody Conteudo conteudo){
        return ResponseEntity.status(HttpStatus.OK).body(conteudoService.update(id,conteudo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable  Long id){
        conteudoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
