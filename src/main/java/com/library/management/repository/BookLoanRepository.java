// src/main/java/com/library/repository/BookLoanRepository.java
package com.library.management.repository;

import com.library.management.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    // 按借阅日期排序查询
    List<BookLoan> findAllByOrderByLoanDateDesc();
}