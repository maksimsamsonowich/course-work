package by.grsu.coursework.repository;

import by.grsu.coursework.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {
}
