package by.grsu.coursework.repository;

import by.grsu.coursework.model.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepo extends CrudRepository<Faculty, Long> {

    Faculty getFacultyByTitle(String title);

}
