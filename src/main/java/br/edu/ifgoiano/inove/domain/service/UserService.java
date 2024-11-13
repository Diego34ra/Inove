package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.*;
import br.edu.ifgoiano.inove.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserSimpleOutputDTO> list();

    User findById(Long id);

    @Transactional
    User create(UserInputDTO newUser);

    @Transactional
    StudentOutputDTO create (Long schoolId, StudentInputDTO newStudentDTO);

    User update (Long id, User user);

    void deleteById(Long Id);

    List<User> listUserByRole(String role);

    List<UserOutputDTO> listAdmins();

    List<StudentOutputDTO> listStudents();

    List<UserOutputDTO> listInstructors();

    UserDetails findByEmail(String email);

    boolean emailExists(String email);

    boolean cpfExists(String cpf);

//    UserDetails loadByUsername(String username) throws UsernameNotFoundException;
//
//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
