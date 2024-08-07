package br.edu.ifgoiano.inove.controller;

import br.edu.ifgoiano.inove.controller.dto.AdminOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.DiscenteOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.InstrutorOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.EscolaNotFoundException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Escola;
import br.edu.ifgoiano.inove.domain.model.Usuario;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import br.edu.ifgoiano.inove.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inove/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private MyModelMapper mapper;

    @GetMapping
    public List<Usuario> listUsers(){
        return userService.list();
    }

    @GetMapping("/admin")
    public List<AdminOutputDTO> listAdmins(){
        return userService.listAdmins();
    }

    @GetMapping("/discente")
    public List<DiscenteOutputDTO> listStudents(){
        return userService.listStudents();
    }

    @GetMapping("/instrutor")
    public List<InstrutorOutputDTO> listInstructors(){
        return userService.listInstructors();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findEscola(@PathVariable String userId){
        try {
            Usuario savedUser = userService.findById(Long.parseLong(userId));

            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }catch(ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/admin")
    public AdminOutputDTO createAdmin(@RequestBody Usuario admin){
        admin.setTipo(UsuarioRole.ADMINISTRATOR);
        return mapper.mapTo(userService.create(admin), AdminOutputDTO.class);
    }

    @PostMapping("/discente")
    public DiscenteOutputDTO createStudent(@RequestBody Usuario student){
        student.setTipo(UsuarioRole.DISCENTE);
        return mapper.mapTo(userService.create(student.getEscola().getId(), student), DiscenteOutputDTO.class);
    }

    @PostMapping("/instrutor")
    public InstrutorOutputDTO createInstructor(@RequestBody Usuario instructor){
        instructor.setTipo(UsuarioRole.INSTRUTOR);
        return mapper.mapTo(userService.create(instructor), InstrutorOutputDTO.class);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Usuario user){
        Usuario updatedEscola = userService.update(userId, user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedEscola);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
            userService.deleteById(Long.parseLong(userId));
            return ResponseEntity.noContent().build();
    }
}
