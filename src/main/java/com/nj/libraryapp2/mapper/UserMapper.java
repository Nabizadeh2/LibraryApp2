package com.nj.libraryapp2.mapper;

import com.nj.libraryapp2.dao.entity.UserEntity;
import com.nj.libraryapp2.dto.user.UserRequestDto;
import com.nj.libraryapp2.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", constant = "USER")
    UserEntity toEntity(UserRequestDto userRequestDto);

    UserResponseDto toDto(UserEntity user);
}

