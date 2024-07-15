package br.edu.ifgoiano.inove.api.controller;

import br.edu.ifgoiano.inove.domain.exceptions.EscolaNotFoundException;
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
    private EscolaService escolaService;

    @GetMapping
    public List<Escola> listEscolas(){
        return escolaService.list();
    }

    @GetMapping("/{escolaId}")
    public ResponseEntity<?> findEscola(@PathVariable String escolaId){
        try {
            Escola savedEscola = escolaService.findById(Long.parseLong(escolaId));

            return ResponseEntity.status(HttpStatus.OK).body(savedEscola);
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public Escola addEscola(@RequestBody @Validated  Escola escola){
        return escolaService.add(escola);
    }

    @PutMapping("/{escolaId}")
    public ResponseEntity<?> UpdateEscola(@PathVariable String escolaId, @RequestBody @Validated Escola escola){
        try {
            Escola updatedEscola = escolaService.update(Long.parseLong(escolaId), escola);

            return ResponseEntity.status(HttpStatus.OK).body(updatedEscola);
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{escolaId}")
    public ResponseEntity deleteEscola(@PathVariable String escolaId){

        escolaService.deleteById(Long.parseLong(escolaId));
        return ResponseEntity.noContent().build();
    }
}
