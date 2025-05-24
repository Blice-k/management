// src/main/java/com/library/controller/BookLoanController.java
package com.library.management.controller;

import com.library.management.entity.BookLoan;
import com.library.management.service.impl.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/book_loans")
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    // 显示借阅记录列表
    @GetMapping
    public String listLoans(@RequestParam(required = false) String sort, Model model) {
        List<BookLoan> loans;

        if ("alpha".equals(sort)) {
            // 按日期排序
            loans = bookLoanService.getLoansSortedByDate();
        } else {
            // 默认排序
            loans = bookLoanService.getAllLoans();
        }

        model.addAttribute("loans", loans);
        return "book_loans"; // 返回book_loans.html模板
    }

    // 显示添加借阅记录表单
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("loan", new BookLoan());
        return "add-loan"; // 需要创建这个模板
    }

    // 处理添加借阅记录请求
    @PostMapping("/add")
    public String addLoan(@ModelAttribute BookLoan loan, RedirectAttributes redirectAttributes) {
        try {
            bookLoanService.saveLoan(loan);
            redirectAttributes.addFlashAttribute("message", "Loan added successfully!");
            return "redirect:/book_loans";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add loan: " + e.getMessage());
            return "redirect:/book_loans/add";
        }
    }

    // 显示编辑借阅记录表单
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookLoan loan = bookLoanService.getLoanById(id);
        if (loan != null) {
            model.addAttribute("loan", loan);
            return "edit-loan"; // 需要创建这个模板
        } else {
            return "redirect:/book_loans";
        }
    }

    // 处理编辑借阅记录请求
    @PostMapping("/edit/{id}")
    public String editLoan(@PathVariable Long id, @ModelAttribute BookLoan loan, RedirectAttributes redirectAttributes) {
        try {
            loan.setLoanId(id); // 确保ID正确
            bookLoanService.saveLoan(loan);
            redirectAttributes.addFlashAttribute("message", "Loan updated successfully!");
            return "redirect:/book_loans";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update loan: " + e.getMessage());
            return "redirect:/book_loans/edit/" + id;
        }
    }

    // 处理删除借阅记录请求
    @GetMapping("/delete/{id}")
    public String deleteLoan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookLoanService.deleteLoan(id);
            redirectAttributes.addFlashAttribute("message", "Loan deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete loan: " + e.getMessage());
        }
        return "redirect:/book_loans";
    }
}