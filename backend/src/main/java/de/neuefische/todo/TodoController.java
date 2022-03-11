package de.neuefische.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todos")
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);

    }

    @GetMapping
    public Collection<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String id) {
        return ResponseEntity.of(todoService.getTodo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> changeTodo(@PathVariable String id, @RequestBody Todo todo) {
        return ResponseEntity.of(todoService.updateTask(id, todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> patchJoke(@PathVariable String id, @RequestBody Todo todo) {
        return ResponseEntity.of(todoService.patchTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable String id) {
        return ResponseEntity.of(todoService.deleteTodo(id));
    }

}
