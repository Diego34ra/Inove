package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
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
    public List<Escola> listEscola(){
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
    public Escola createEscola(@RequestBody @Validated  Escola escola){
        return escolaService.create(escola);
    }

    @PutMapping("/{escolaId}")
    public ResponseEntity<?> updateEscola(@PathVariable Long escolaId, @RequestBody Escola escola){
        Escola updatedEscola = escolaService.update(escolaId, escola);

        return ResponseEntity.status(HttpStatus.OK).body(updatedEscola);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{escolaId}")
    public ResponseEntity<?> deleteEscola(@PathVariable String escolaId){
            escolaService.deleteById(Long.parseLong(escolaId));
            return ResponseEntity.noContent().build();
    }
}
