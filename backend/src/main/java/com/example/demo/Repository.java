package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@org.springframework.stereotype.Repository
public class Repository {

    List<Task> taskrepo = new ArrayList<>();


    public void addtask(Task newtask) {
        taskrepo.add(newtask);
    }

    public List<Task> getalltask() {
        return taskrepo;
    }

    public Optional<Task> gettask(String id) {
        return taskrepo.stream()
                .filter(p -> Objects.equals(id, p.getId()))
                .findFirst();
    }

    public void deletetask(String id) {
        for (Task task : taskrepo) {
            if (task.getId().equals(id)) {
                taskrepo.remove(task);
                break;
            }
        }

    }
}


