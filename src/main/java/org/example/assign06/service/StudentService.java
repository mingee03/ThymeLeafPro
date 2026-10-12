package org.example.assign06.service;

import org.example.assign06.domain.Student;
import org.example.assign06.dto.*;
import org.example.assign06.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentResponse create(StudentRequest r) {
        return toResponse(repository.save(new Student(null, r.name(), r.age(), r.email(), r.major(), r.studentId())));
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(student.getId(), student.getName(), student.getAge(), student.getEmail(),
                student.getMajor(), student.getStudentId());
    }

    public List<StudentResponse> findall(String studentId) {
        List<Student> students = repository.findAll();
        if (studentId != null) {
            students = students.stream().filter(s -> s.getStudentId().equalsIgnoreCase(studentId)).toList();
        }
        return students.stream().map(this::toResponse).toList();
    }

    public StudentResponse update(Long id, StudentRequest r) {
        Student s = findStudent(id);
        s.setName(r.name());
        s.setAge(r.age());
        s.setEmail(r.email());
        s.setMajor(r.major());
        s.setStudentId(r.studentId());
        return toResponse(repository.update(s));
    }

    public StudentResponse findById(Long id) {
        return toResponse(findStudent(id));
    }

    private Student findStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public void delete(Long id) {
        findStudent(id);
        repository.deleteById(id);
    }
}
