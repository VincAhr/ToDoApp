package de.neuefische.todo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<Todo> createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        return todoService.getTodos();
    }

    @GetMapping
    public Collection<Todo> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable String id) {
        return todoService.getTodo(id);
    }

    @PutMapping("/{id}")
    public Collection<Todo> changeTodo(@PathVariable String id, @RequestBody Todo todo) {
        todoService.changeTodo(id, todo);
        return todoService.getTodos();
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }

}
