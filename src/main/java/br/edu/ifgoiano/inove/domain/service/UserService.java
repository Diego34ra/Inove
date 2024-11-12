package br.edu.ifgoiano.inove.domain.service;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserSimpleOutputDTO;
import br.edu.ifgoiano.inove.domain.model.User;

import java.util.List;

public interface UserService {
    List<UserSimpleOutputDTO> list();

    User findById(Long id);

    User create (User newUser);

    StudentOutputDTO create (Long schoolId, StudentInputDTO newStudentDTO);

    User update (Long id, User user);

    void deleteById(Long Id);

    List<User> listUserByRole(String role);

    List<UserOutputDTO> listAdmins();

    List<StudentOutputDTO> listStudents();

    List<UserOutputDTO> listInstructors();
}
