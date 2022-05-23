package by.grsu.coursework.repository;

import by.grsu.coursework.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepo extends CrudRepository<Test, Long> {
}
