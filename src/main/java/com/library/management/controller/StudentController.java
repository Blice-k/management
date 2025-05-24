// src/main/java/com/library/controller/StudentController.java
package com.library.management.controller;

import com.library.management.entity.Student;
import com.library.management.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(@RequestParam(required = false) String sort, Model model) {
        if ("alpha".equals(sort)) {
            model.addAttribute("students", studentService.getAllStudentsSortedByFirstName());
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "students"; // 返回students.html模板
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student"; // 需要创建这个模板
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        try {
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("message", "Student added successfully!");
            return "redirect:/students";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add student: " + e.getMessage());
            return "redirect:/students/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "edit-student"; // 需要创建这个模板
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        try {
            student.setId(Math.toIntExact(id));
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
            return "redirect:/students";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update student: " + e.getMessage());
            return "redirect:/students/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete student: " + e.getMessage());
        }
        return "redirect:/students";
    }
}