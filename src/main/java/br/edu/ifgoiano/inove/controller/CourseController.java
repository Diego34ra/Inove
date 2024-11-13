package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.courseDTOs.CourseInputDTO;
import br.edu.ifgoiano.inove.domain.model.Content;
import br.edu.ifgoiano.inove.domain.model.Course;
import br.edu.ifgoiano.inove.domain.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
public class CourseController {

    @Autowired
    private CourseService cursoService;

    @PostMapping
    @Operation(summary = "Cria um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Coteudo adicionado com sucesso.",content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Course> create(@RequestBody CourseInputDTO curso){
        var cursoCreated = cursoService.create(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreated);
    }

    @GetMapping
    @Operation(summary = "Listar cursos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso.",
                    content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ContentSimpleOutputDTO.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<Course>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Coteudo encontrado com sucesso.",content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = Content.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Course> findOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findById(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Coteudo atualizado com sucesso.",content = { @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @Schema(implementation = Content.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Course> update(@PathVariable  Long id, @RequestBody Course curso){
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
