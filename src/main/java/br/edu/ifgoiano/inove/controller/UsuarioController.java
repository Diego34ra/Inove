package br.edu.ifgoiano.inove.controller;

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

    @GetMapping
    public List<Usuario> listUsers(){
        return userService.list();
    }

    @GetMapping("/admin")
    public List<Usuario> listAdmins(){
        return userService.listUserByRole(UsuarioRole.ADMINISTRATOR.name());
    }

    @GetMapping("/discente")
    public List<Usuario> listStudents(){
        return userService.listUserByRole(UsuarioRole.DISCENTE.name());
    }

    @GetMapping("/instrutor")
    public List<Usuario> listInstructors(){
        return userService.listUserByRole(UsuarioRole.INSTRUTOR.name());
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
    public Usuario createAdmin(@RequestBody Usuario admin){
        admin.setTipo(UsuarioRole.ADMINISTRATOR);
        return userService.create(admin);
    }

    @PostMapping("/discente")
    public Usuario createStudent(@RequestBody Usuario student){
        student.setTipo(UsuarioRole.DISCENTE);
        return userService.create(student);
    }

    @PostMapping("/instrutor")
    public Usuario createInstructor(@RequestBody Usuario instructor){
        instructor.setTipo(UsuarioRole.INSTRUTOR);
        return userService.create(instructor);
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
