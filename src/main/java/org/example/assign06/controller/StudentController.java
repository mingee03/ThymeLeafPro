package org.example.assign06.controller;

import org.example.assign06.dto.StudentRequest;
import org.example.assign06.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", studentService.findall(null));
        return "students/list";
    }

    @GetMapping("/students/new")
    public String form() {
        return "students/form";
    }

    @PostMapping("/students")
    public String create(@ModelAttribute StudentRequest request) {
        studentService.create(request);
        return "redirect:/students";
    }
}
