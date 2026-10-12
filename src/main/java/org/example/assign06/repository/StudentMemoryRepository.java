package org.example.assign06.repository;

import org.example.assign06.domain.Student;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StudentMemoryRepository implements StudentRepository {

    private final Map<Long, Student> store = new LinkedHashMap<>();
    private long nextId = 1L;

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
        }
        store.put(student.getId(), student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Student update(Student student) {
        store.put(student.getId(), student);
        return student;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
