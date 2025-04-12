package com.nj.libraryapp2.mapper;

import com.nj.libraryapp2.dao.entity.RentBookEntity;
import com.nj.libraryapp2.dto.rent.RentRequestDto;
import com.nj.libraryapp2.dto.rent.RentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentMapper {
        @Mapping(source = "bookId", target = "book.id")
   @Mapping(source = "userId", target = "user.id")
    RentBookEntity toEntity(RentRequestDto requestDto);
        @Mapping(source = "book.id", target = "bookId")
   @Mapping(source = "user.id", target = "userId")
    RentResponseDto toDto(RentBookEntity rentBook);
}
