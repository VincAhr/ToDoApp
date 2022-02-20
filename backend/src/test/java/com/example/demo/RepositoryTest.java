package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {


    @Test
    void addnewtask(){
        Repository repotest = new Repository();
        repotest.addtask( new Task("1", "Hallo", "testing"));
        repotest.addtask( new Task("2", "Tach", "testing"));
        List<Task> actual = repotest.getalltask();

        assertTrue(actual.size() == 2);
    }

    @Test
    void deletetask(){
        Repository repotest = new Repository();
        repotest.addtask( new Task("1", "Hallo", "testing"));
        repotest.addtask( new Task("2", "Tach", "testing"));
        repotest.deletetask("2");
        List<Task> actual = repotest.getalltask();

        List<Task> result = repotest.getalltask();
        assertTrue(result.size() == 1);

    }






}