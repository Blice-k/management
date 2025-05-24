// src/main/java/com/library/entity/BookLoan.java
package com.library.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Long bookId;      // 关联图书ID
    private Long studentId;   // 关联学生ID

    private LocalDate loanDate;    // 借阅日期
    private LocalDate returnDate;  // 归还日期
}