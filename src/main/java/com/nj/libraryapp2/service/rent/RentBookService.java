package com.nj.libraryapp2.service.rent;

import com.nj.libraryapp2.dto.rent.RentRequestDto;
import com.nj.libraryapp2.dto.rent.RentResponseDto;

import java.util.List;

public interface RentBookService {
    List<RentResponseDto> getAllRents();
    RentResponseDto getRentById(Long id);
    void saveRent(RentRequestDto requestDto);
    void updateRent(Long id, RentRequestDto rentDto);
    void deleteRentById(Long id);
    List<RentResponseDto> getActiveRents();
}
