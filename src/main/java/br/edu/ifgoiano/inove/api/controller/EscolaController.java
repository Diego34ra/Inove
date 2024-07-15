package br.edu.ifgoiano.inove.api.controller;

import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Escola findEscola(@PathVariable String escolaId){

        return escolaService.findById(Long.parseLong(escolaId));
    }

    @PostMapping
    public Escola addEscola(@RequestBody @Validated  Escola escola){

        return escolaService.add(escola);
    }

    @PutMapping("/{escolaId}")
    public Escola UpdateEscola(@PathVariable String escolaId, @RequestBody @Validated Escola escola){

        return escolaService.update(Long.parseLong(escolaId), escola);
    }

    @DeleteMapping("/{escolaId}")
    public void deleteEscola(@PathVariable String escolaId){

        escolaService.deleteById(Long.parseLong(escolaId));
    }
}
