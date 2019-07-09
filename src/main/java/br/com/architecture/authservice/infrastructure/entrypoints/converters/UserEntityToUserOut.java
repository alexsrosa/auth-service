package br.com.architecture.authservice.infrastructure.entrypoints.converters;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserOutDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserOut implements Converter<UserEntity, UserOutDto> {

    private final ModelMapper modelMapper;

    public UserEntityToUserOut(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserOutDto convert(UserEntity source) {
        return modelMapper.map(source, UserOutDto.class);
    }
}
