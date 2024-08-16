package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.ConteudoSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Listar escola")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Escola.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public List<Escola> list(){
        return schoolService.list();
    }

    @GetMapping("/{schoolId}")
    @Operation(summary = "Buscar uma escola")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Escola encontrada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Escola.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> findOne(@PathVariable String escolaId){
        try {
            Escola savedSchool = schoolService.findById(Long.parseLong(escolaId));

            return ResponseEntity.status(HttpStatus.OK).body(savedSchool);
        }catch(EscolaNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Cria uma escola")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Escola adicionado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Escola.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public Escola create(@RequestBody @Validated  Escola school){
        return schoolService.create(school);
    }

    @PutMapping("/{schoolId}")
    @Operation(summary = "Atualiza uma Escola")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Escola atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Escola.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> update(@PathVariable Long schoolId, @RequestBody Escola school){
        Escola updatedSchool = schoolService.update(schoolId, school);

        return ResponseEntity.status(HttpStatus.OK).body(updatedSchool);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{schoolId}")
    @Operation(summary = "Remove uma escola")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Escola deletada com sucesso."),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable String schoolId){
            schoolService.deleteById(Long.parseLong(schoolId));
            return ResponseEntity.noContent().build();
    }
}
