package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.request.userDTOs.*;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceBadRequestException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<UserSimpleOutputDTO> list() {
        return mapper.toList(userRepository.findAll(), UserSimpleOutputDTO.class);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum usuario com esse id."));
    }

    @Override
    @Transactional
    public User create(UserInputDTO newUserDTO) {
        User newUser = mapper.mapTo(newUserDTO,User.class);

        if (emailExists(newUser.getEmail()))
            throw new ResourceBadRequestException("Esse email já está cadastrado!");

        if(cpfExists(newUser.getCpf()))
            throw new ResourceBadRequestException("Esse CPF á está cadastrado!");

        String encryptedPasswrod = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(encryptedPasswrod);

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public StudentOutputDTO create(Long schoolId, StudentInputDTO newUserDTO) {

       var userCreate = mapper.mapTo(newUserDTO, User.class);

       if (userCreate.getRole() == null)
           userCreate.setRole(UserRole.STUDENT);

       if (emailExists(userCreate.getEmail()))
           throw new ResourceBadRequestException("Esse email já está cadastrado!");

       if(cpfExists(userCreate.getCpf()))
           throw new ResourceBadRequestException("Esse CPF á está cadastrado!");

       String encryptedPasswrod = new BCryptPasswordEncoder().encode(userCreate.getPassword());
       userCreate.setPassword(encryptedPasswrod);

       userCreate.setSchool(userCreate.getSchool());

       return mapper.mapTo(userRepository.save(userCreate), StudentOutputDTO.class);
    }

    @Override
    @Transactional
    public User update(Long id, User userUpdate) {
        User user = findById(id);
        BeanUtils.copyProperties(userUpdate, user, inoveUtils.getNullPropertyNames(userUpdate));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            User user = findById(id);
            userRepository.delete(user);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }

    @Override
    public List<User> listUserByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<UserOutputDTO> listAdmins() {
        return mapper.toList(userRepository.findByRole(UserRole.ADMINISTRATOR.name())
                , UserOutputDTO.class);
    }

    @Override
    public List<StudentOutputDTO> listStudents() {
        return mapper.toList(userRepository.findByRole(UserRole.STUDENT.name())
                , StudentOutputDTO.class);
    }

    @Override
    public List<UserOutputDTO> listInstructors() {
        return mapper.toList(userRepository.findByRole(UserRole.INSTRUCTOR.name())
                , UserOutputDTO.class);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean cpfExists(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    @Override
    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//
//    @Override
//    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
