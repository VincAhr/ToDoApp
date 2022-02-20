package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @Autowired
     TestRestTemplate restTemplate;


    @Test
    void integrationsTest(){
        Task mach = new Task("1", "test", "inprogress");
        Task mach1 = new Task("2", "test1", "inprogress");

        restTemplate.postForEntity("/todoapp/tasks",mach,Void.class);
        restTemplate.postForEntity("/todoapp/tasks",mach1,Void.class);
        Task[] tasks = restTemplate.getForObject("/todoapp/tasks",Task[].class);
        assertTrue(tasks.length == 2);
    }














}