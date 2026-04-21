package com.example.studentmanagement.controller;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentDTO createStudent(@Validated @RequestBody StudentDTO studentDTO) {
        return service.createStudent(studentDTO);
    }

    @GetMapping
    public Page<StudentDTO> getAllStudents(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getAllStudents(page, size);
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id,
                                    @RequestBody StudentDTO studentDTO) {

        return service.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        service.deleteStudent(id);
        return "Student deleted successfully";
    }

    @GetMapping("/search")
    public List<StudentDTO> searchStudent(@RequestParam String name) {

        return service.getStudentsByName(name);
    }
}