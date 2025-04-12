package com.nj.libraryapp2.service.book;

import com.nj.libraryapp2.dto.book.BookRequestDto;
import com.nj.libraryapp2.dto.book.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBook();
    BookResponseDto getBookById(Long id);
    void saveBook(BookRequestDto bookDto);
    void updateBook(Long id, BookRequestDto bookDto);
    void deleteBook(Long id);
}
