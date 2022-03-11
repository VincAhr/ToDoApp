package de.neuefische.todo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Todo implements Comparable<Todo> {
    private String id = "";
    private String task = "";
    private String description = "";
    private TodoStatus status = TodoStatus.Open;

    public Todo(String task) {
        this.task = task;
    }


    public Todo patchTask(Todo todo) {
        if (todo.getTask() != null) {
            setTask(todo.getTask());
        }

        return this;
    }

    public Todo update(Todo todo) {
        setTask(todo.getTask());
        return this;
    }

    @Override
    public int compareTo(Todo todo) {
        if (status == todo.getStatus()) {
            return 0;
        } else if (status == TodoStatus.Open) {
            return -1;
        }
        return 1;
    }
}