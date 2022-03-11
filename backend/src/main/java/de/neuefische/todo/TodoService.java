package de.neuefische.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;


    public Todo createTodo(Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

    public Collection<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodo(String id) {
        return todoRepository.findById(id);
    }

    public Optional<Todo> deleteTodo(String id) {
        return todoRepository.findById(id)
                .map(j -> {
                    todoRepository.deleteById(id);
                    return j;
                });
    }

//    public Optional<Todo> patchTodo(String id, Todo todo) {
//        return todoRepository.findById(id)
//                .map(j -> j.patchTask(todo))
//                .map(todoRepository::save);
//    }

    public Optional<Todo> updateTask(String id, Todo todo) {
        return todoRepository.findById(id)
                .map(j -> j.update(todo))
                .map(todoRepository::save);
    }
}

