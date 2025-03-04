package com.waw.majorproject2.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("api/test")
    public String testAPI(){
        return "Hello world...";
    }
}
