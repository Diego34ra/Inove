package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.StudentOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.User;
import br.edu.ifgoiano.inove.domain.model.UserRole;
import br.edu.ifgoiano.inove.domain.repository.UserRepository;
import br.edu.ifgoiano.inove.domain.service.SchoolService;
import br.edu.ifgoiano.inove.domain.service.UserService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRespository;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<User> list() {
        return userRespository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum usuario com esse id."));
    }

    @Override
    @Transactional
    public User create(User newUser) {
        return userRespository.save(newUser);
    }

    @Override
    public User create(Long schoolId, User newUser) {
        newUser.setSchool(schoolService.findById(schoolId));
        User savedUser = userRespository.save(newUser);
        return savedUser;
    }

    @Override
    @Transactional
    public User update(Long id, User userUpdate) {
        User user = findById(id);
        BeanUtils.copyProperties(userUpdate, user, inoveUtils.getNullPropertyNames(userUpdate));
        return userRespository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            User user = findById(id);
            userRespository.delete(user);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }

    @Override
    public List<User> listUserByRole(String role) {
        return userRespository.findByRole(role);
    }

    @Override
    public List<UserOutputDTO> listAdmins() {
        return mapper.toList(userRespository.findByRole(UserRole.ADMINISTRATOR.name())
                , UserOutputDTO.class);
    }

    @Override
    public List<StudentOutputDTO> listStudents() {
        return mapper.toList(userRespository.findByRole(UserRole.STUDENT.name())
                , StudentOutputDTO.class);
    }

    @Override
    public List<UserOutputDTO> listInstructors() {
        return mapper.toList(userRespository.findByRole(UserRole.INSTRUCTOR.name())
                , UserOutputDTO.class);
    }
}
