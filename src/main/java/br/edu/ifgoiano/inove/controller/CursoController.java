package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.ConteudoSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.Conteudo;
import br.edu.ifgoiano.inove.domain.model.Curso;
import br.edu.ifgoiano.inove.domain.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Coteudo adicionado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Curso> create(@RequestBody Curso curso){
        var cursoCreated = cursoService.create(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreated);
    }

    @GetMapping
    @Operation(summary = "Listar cursos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConteudoSimpleOutputDTO.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<Curso>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Coteudo encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Conteudo.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Curso> findOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findById(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Coteudo atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Conteudo.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Curso> update(@PathVariable  Long id, @RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.update(id,curso));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso."),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable  Long id){
        cursoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
