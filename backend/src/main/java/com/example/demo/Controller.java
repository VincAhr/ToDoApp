package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/todoapp")
public class Controller {


    @Autowired
    private Repository todo;

    @GetMapping("/tasks")
    public List<Task> alltasks() {
        return todo.getalltask();
    }

    @PostMapping("/tasks")
    public void addtask(@RequestBody Task newtask) {
        todo.addtask(newtask);
    }

    @DeleteMapping("/{id}")
    public void deletetask(@PathVariable("id") String id) {
        todo.deletetask(id);
    }


}
