package com.nj.libraryapp2.dto.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookResponseDto {
    Long id;
    String name;
    String writer;
    String publisher;
    LocalDate publishYear;
    String description;
}
