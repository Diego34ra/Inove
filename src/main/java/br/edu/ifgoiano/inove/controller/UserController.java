package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.*;
import br.edu.ifgoiano.inove.domain.service.UserService;
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
@RequestMapping("api/inove/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyModelMapper mapper;

    @GetMapping
    @Operation(summary = "Listar Usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuarios listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public List<User> listUsers(){
        return userService.list();
    }

    @GetMapping("/admin")
    @Operation(summary = "Listar Adiministradores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adiministradores listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserOutputDTO.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public List<UserOutputDTO> listAdmins(){
        return userService.listAdmins();
    }

    @GetMapping("/discente")
    @Operation(summary = "Listar discentes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Discentes listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StudentOutputDTO.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public List<StudentOutputDTO> listStudents(){
        return userService.listStudents();
    }

    @GetMapping("/instrutor")
    @Operation(summary = "Listar instrutores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Instrutores listados com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserOutputDTO.class)))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public List<UserOutputDTO> listInstructors(){
        return userService.listInstructors();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Buscar um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> findUser(@PathVariable String userId){
        try {
            User savedUser = userService.findById(Long.parseLong(userId));

            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }catch(ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Cria um usuario interno.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario adicionado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Course.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public UserOutputDTO createAdmin(@RequestBody User admin){
        admin.setRole(UserRole.ADMINISTRATOR);
        return mapper.mapTo(userService.create(admin), UserOutputDTO.class);
    }

    @PostMapping("/discente")
    public StudentOutputDTO createStudent(@RequestBody User student){
        student.setRole(UserRole.STUDENT);
        return mapper.mapTo(userService.create(student.getSchool().getId(), student), StudentOutputDTO.class);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Atualiza um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user){
        User updatedEscola = userService.update(userId, user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedEscola);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    @Operation(summary = "Remove um curso")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso."),
            //@ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
            userService.deleteById(Long.parseLong(userId));
            return ResponseEntity.noContent().build();
    }
}
