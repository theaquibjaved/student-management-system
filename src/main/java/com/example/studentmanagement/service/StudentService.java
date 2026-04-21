package com.example.studentmanagement.service;



import java.util.List;

import org.springframework.data.domain.Page;

import com.example.studentmanagement.dto.StudentDTO;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    Page<StudentDTO> getAllStudents(int page, int size);

    StudentDTO getStudentById(Long id);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);

    List<StudentDTO> getStudentsByName(String name);
}
