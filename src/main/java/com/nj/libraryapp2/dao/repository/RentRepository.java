package com.nj.libraryapp2.dao.repository;

import com.nj.libraryapp2.dao.entity.RentBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<RentBookEntity,Long> {
    @Query("select r from RentBookEntity r where r.status = 'ACTIVE'")
    List<RentBookEntity> findRentBooksByActiveStatus();
}
