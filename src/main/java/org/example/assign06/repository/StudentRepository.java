package org.example.assign06.repository;

import org.example.assign06.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student update(Student student);

    void deleteById(Long id);

}
