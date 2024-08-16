package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.UserOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.DiscenteOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Usuario;
import br.edu.ifgoiano.inove.domain.model.UsuarioRole;
import br.edu.ifgoiano.inove.domain.repository.UsuarioRepository;
import br.edu.ifgoiano.inove.domain.service.EscolaService;
import br.edu.ifgoiano.inove.domain.service.UsuarioService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository userRespository;

    @Autowired
    private EscolaService schoolService;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<Usuario> list() {
        return userRespository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum usuario com esse id."));
    }

    @Override
    @Transactional
    public Usuario create(Usuario newUser) {
        return userRespository.save(newUser);
    }

    @Override
    public Usuario create(Long schoolId, Usuario newUser) {
        newUser.setEscola(schoolService.findById(schoolId));
        Usuario savedUser = userRespository.save(newUser);
        return savedUser;
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario userUpdate) {
        Usuario user = findById(id);
        BeanUtils.copyProperties(userUpdate, user, inoveUtils.getNullPropertyNames(userUpdate));
        return userRespository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try{
            Usuario user = findById(id);
            userRespository.delete(user);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }

    @Override
    public List<Usuario> listUserByRole(String role) {
        return userRespository.findByTipoContaining(role);
    }

    @Override
    public List<UserOutputDTO> listAdmins() {
        return mapper.toList(userRespository.findByTipoContaining(UsuarioRole.ADMINISTRATOR.name())
                , UserOutputDTO.class);
    }

    @Override
    public List<DiscenteOutputDTO> listStudents() {
        return mapper.toList(userRespository.findByTipoContaining(UsuarioRole.DISCENTE.name())
                , DiscenteOutputDTO.class);
    }

    @Override
    public List<UserOutputDTO> listInstructors() {
        return mapper.toList(userRespository.findByTipoContaining(UsuarioRole.INSTRUTOR.name())
                , UserOutputDTO.class);
    }
}
