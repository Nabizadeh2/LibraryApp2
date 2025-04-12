package com.nj.libraryapp2.mapper;


import com.nj.libraryapp2.dao.entity.BookEntity;
import com.nj.libraryapp2.dto.book.BookRequestDto;
import com.nj.libraryapp2.dto.book.BookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
         unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookEntity toEntity(BookRequestDto bookRequestDto);
    BookResponseDto toDto (BookEntity bookEntity);
}
