package com.example.studentmanagement.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        Student saved = repository.save(student);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public Page<StudentDTO> getAllStudents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Student> students = repository.findAll(pageable);

        return students.map(student -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setCourse(student.getCourse());
            return dto;
        });
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());

        return dto;
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        repository.save(student);

        dto.setId(student.getId());

        return dto;
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        repository.delete(student);
    }

    @Override
    public List<StudentDTO> getStudentsByName(String name) {

        return repository.findByName(name)
                .stream()
                .map(student -> {
                    StudentDTO dto = new StudentDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setEmail(student.getEmail());
                    dto.setCourse(student.getCourse());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}