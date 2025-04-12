package com.nj.libraryapp2.service.rent;

import com.nj.libraryapp2.dao.entity.BookEntity;
import com.nj.libraryapp2.dao.entity.RentBookEntity;
import com.nj.libraryapp2.dao.entity.UserEntity;
import com.nj.libraryapp2.dao.repository.BookRepository;
import com.nj.libraryapp2.dao.repository.RentRepository;
import com.nj.libraryapp2.dao.repository.UserRepository;
import com.nj.libraryapp2.dto.rent.RentRequestDto;
import com.nj.libraryapp2.dto.rent.RentResponseDto;
import com.nj.libraryapp2.enums.Status;
import com.nj.libraryapp2.exception.BookNotFoundException;
import com.nj.libraryapp2.exception.RentNotFoundException;
import com.nj.libraryapp2.exception.UserNotFoundException;
import com.nj.libraryapp2.mapper.RentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentBookServiceImpl implements RentBookService {
    private final RentRepository rentRepo;
    private final RentMapper rentMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepo;

    @Override
    public List<RentResponseDto> getAllRents() {
        return rentRepo.findAll().stream().map(rentMapper::toDto).toList();
    }

    @Override
    public RentResponseDto getRentById(Long id) {
        return rentRepo.findById(id).map(rentMapper::toDto).orElseThrow(() ->
                new RentNotFoundException("Rent Not Found by id: " + id));
    }

    @Transactional
    @Override
    public void saveRent(RentRequestDto requestDto) {
        RentBookEntity rentBook = rentMapper.toEntity(requestDto);
        BookEntity book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id: " + requestDto.getBookId()));
        UserEntity user = userRepo.findById(requestDto.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id: " + requestDto.getUserId()));
        rentBook.setBook(book);
        rentBook.setUser(user);
        if (!book.isRented()) {
            book.setRented(true);
            book.setUser(user);
            bookRepository.save(book);
            rentBook.setStatus(Status.ACTIVE);
            rentRepo.save(rentBook);
        }
    }

    @Override
    public void updateRent(Long id, RentRequestDto requestDto) {
        RentBookEntity rentBook = rentMapper.toEntity(requestDto);
        rentBook.setId(id);
        rentRepo.save(rentBook);
    }

    @Transactional
    @Override
    public void deleteRentById(Long id) {
        RentBookEntity rentBook = rentRepo.findById(id).orElseThrow(() ->
                new RentNotFoundException("Rent Not Found by id: " + id));
        if (rentBook.getStatus().equals(Status.ACTIVE)) {
            rentBook.setStatus(Status.DELETED);
            rentRepo.save(rentBook);
        }
        BookEntity book = bookRepository.findById(rentBook.getBook().getId()).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id: " + rentBook.getBook().getId()));
        if (book.isRented()) {
            book.setRented(false);
            book.setUser(null);
            bookRepository.save(book);
        }
    }

    @Override
    public List<RentResponseDto> getActiveRents() {
        return rentRepo.findRentBooksByActiveStatus().stream().map(rentMapper::toDto).toList();
    }
}
