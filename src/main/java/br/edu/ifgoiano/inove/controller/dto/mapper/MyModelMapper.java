package br.edu.ifgoiano.inove.controller.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyModelMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public <S, D> D mapTo(S source, Class<D> destClass) {
        return MODEL_MAPPER.map(source, destClass);
    }

    public <D, T> List<D> toList(List<T> entityList, Class<D> outClass) {
        return entityList.stream()
                .map(entity -> MODEL_MAPPER.map(entity, outClass))
                .collect(Collectors.toList());
    }

}
