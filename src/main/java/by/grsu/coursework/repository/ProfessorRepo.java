package by.grsu.coursework.repository;

import by.grsu.coursework.model.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepo extends CrudRepository<Professor, Long> {
}
