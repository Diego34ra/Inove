package br.edu.ifgoiano.inove.domain.service.implementation;

import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentInputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.request.contentDTOs.ContentSimpleOutputDTO;
import br.edu.ifgoiano.inove.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceInUseException;
import br.edu.ifgoiano.inove.controller.exceptions.ResourceNotFoundException;
import br.edu.ifgoiano.inove.domain.model.Content;
import br.edu.ifgoiano.inove.domain.repository.ContentRepository;
import br.edu.ifgoiano.inove.domain.service.ContentService;
import br.edu.ifgoiano.inove.domain.service.CourseService;
import br.edu.ifgoiano.inove.domain.utils.InoveUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentRepository contentRespository;

    @Autowired
    private SectionServiceImpl sectionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MyModelMapper mapper;

    @Autowired
    private InoveUtils inoveUtils;

    @Override
    public List<ContentSimpleOutputDTO> list(Long sectionId) {
        return mapper.toList(contentRespository.findBySectionId(sectionId), ContentSimpleOutputDTO.class);
    }

    @Override
    public ContentOutputDTO findOneById(Long sectionId, Long contentId) {
        var content = contentRespository.findByIdAndSectionId(contentId, sectionId)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum conteudo com esse id."));
        return mapper.mapTo(content, ContentOutputDTO.class);
    }

    @Override
    public Content findById(Long sectionId, Long contentId) {
        return contentRespository.findByIdAndSectionId(contentId, sectionId)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi possível encontrar nenhum conteudo com esse id."));
    }

    @Override
    @Transactional
    public ContentOutputDTO create(Long courseId, Long sectionId, Content newContent) {
        newContent.setSection(sectionService.findByIdAndCursoId(courseId, sectionId));
        courseService.saveUpdateDate(courseId);
        return mapper.mapTo(contentRespository.save(newContent), ContentOutputDTO.class);
    }

    @Override
    @Transactional
    public ContentOutputDTO update(Long courseId, Long sectionId, Long contentId, ContentInputDTO newContentDTO) {
        Content newContentModel = mapper.mapTo(newContentDTO,Content.class);
        Content savedContent = findById(sectionId, contentId);
        courseService.saveUpdateDate(courseId);
        BeanUtils.copyProperties(newContentModel, savedContent, inoveUtils.getNullPropertyNames(newContentModel));
        return mapper.mapTo(contentRespository.save(savedContent), ContentOutputDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Long courseId, Long sectionId) {
        try{
            Content section = findById(courseId, sectionId);
            contentRespository.delete(section);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceInUseException("O usuário de ID %d esta em uso e não pode ser removido.");
        }
    }
}
