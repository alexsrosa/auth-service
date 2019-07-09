package br.com.architecture.authservice.infrastructure.entrypoints.converters;

import br.com.architecture.authservice.domain.entities.UserEntity;
import br.com.architecture.authservice.infrastructure.entrypoints.dtos.UserInDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInToUserEntity implements Converter<UserInDto, UserEntity> {

    private final ModelMapper modelMapper;

    public UserInToUserEntity(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity convert(UserInDto source) {
        return modelMapper.map(source, UserEntity.class);
    }
}
