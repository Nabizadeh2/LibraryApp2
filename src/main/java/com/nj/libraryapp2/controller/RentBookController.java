package com.nj.libraryapp2.controller;

import com.nj.libraryapp2.dto.rent.RentRequestDto;
import com.nj.libraryapp2.dto.rent.RentResponseDto;
import com.nj.libraryapp2.service.rent.RentBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
public class RentBookController {
    private final RentBookService rentBookService;

    @GetMapping
    public ResponseEntity<List<RentResponseDto>> getAllRents() {
        return ResponseEntity.ok(rentBookService.getAllRents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentResponseDto> getRentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rentBookService.getRentById(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveRent(@RequestBody RentRequestDto requestDto) {
        rentBookService.saveRent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRent(@PathVariable("id") Long id,
                                           @RequestBody RentRequestDto requestDto) {
        rentBookService.updateRent(id, requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable("id") Long id) {
        rentBookService.deleteRentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/active-rents")
    public ResponseEntity<List<RentResponseDto>> getActiveRents(){
        return ResponseEntity.ok(rentBookService.getActiveRents());
    }
}
