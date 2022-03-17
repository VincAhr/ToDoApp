package de.neuefische.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public void createTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public Collection<Todo> getTodos() {
        return todoRepository.findAll()
                .stream()
                .sorted()
                .toList();
    }

    public Todo getTodo(String id) {
        return todoRepository.findById(id).orElse(null);
    }

    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    public void changeTodo(String id, Todo changedTodo) {
        Todo todo = todoRepository.findById(id).orElseThrow();

        todo.setTask(changedTodo.getTask());
        todo.setStatus(changedTodo.getStatus());
        todo.setDescription(changedTodo.getDescription());

        todoRepository.save(todo);
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}

