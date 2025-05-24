package com.library.management.controller;

import com.library.management.entity.Book;
import com.library.management.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(@RequestParam(required = false) String sort, Model model) {
        if ("alpha".equals(sort)) {
            model.addAttribute("books", bookService.getAllBooksSortedByTitle());
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/books";
    }

    // 其他CRUD方法...
}