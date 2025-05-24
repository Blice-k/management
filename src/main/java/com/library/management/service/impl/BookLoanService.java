// src/main/java/com/library/service/BookLoanService.java
package com.library.management.service.impl;

import com.library.management.entity.BookLoan;
import com.library.management.repository.BookLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    // 获取所有借阅记录
    public List<BookLoan> getAllLoans() {
        return bookLoanRepository.findAll();
    }

    // 按日期排序获取借阅记录
    public List<BookLoan> getLoansSortedByDate() {
        return bookLoanRepository.findAllByOrderByLoanDateDesc();
    }

    // 根据ID获取借阅记录
    public BookLoan getLoanById(Long id) {
        return bookLoanRepository.findById(id).orElse(null);
    }

    // 保存借阅记录
    public BookLoan saveLoan(BookLoan loan) {
        // 如果没有设置借阅日期，默认为当前日期
        if (loan.getLoanDate() == null) {
            loan.setLoanDate(LocalDate.now());
        }
        return bookLoanRepository.save(loan);
    }

    // 删除借阅记录
    public void deleteLoan(Long id) {
        bookLoanRepository.deleteById(id);
    }
}