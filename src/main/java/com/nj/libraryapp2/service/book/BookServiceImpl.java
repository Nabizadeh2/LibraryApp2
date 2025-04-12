package com.nj.libraryapp2.service.book;

import com.nj.libraryapp2.dao.entity.BookEntity;
import com.nj.libraryapp2.dao.repository.BookRepository;
import com.nj.libraryapp2.dto.book.BookRequestDto;
import com.nj.libraryapp2.dto.book.BookResponseDto;
import com.nj.libraryapp2.exception.BookNotFoundException;
import com.nj.libraryapp2.mapper.BookMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    BookMapper mapper;

    @Override
    public List<BookResponseDto> getAllBook() {
        return bookRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        return bookRepository.findById(id).map(mapper::toDto).get();
    }

    @Override
    public void saveBook(BookRequestDto requestDto) {
        BookEntity book = mapper.toEntity(requestDto);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookRequestDto requestDto) {
        BookEntity book = mapper.toEntity(requestDto);
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id " + id));
        bookRepository.delete(book);
    }
}
