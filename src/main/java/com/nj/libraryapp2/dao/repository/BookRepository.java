package com.nj.libraryapp2.dao.repository;

import com.nj.libraryapp2.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<BookEntity,Long> {

}
