package de.neuefische.todo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

    List<Todo> findAllByTask(String Todo);

}
