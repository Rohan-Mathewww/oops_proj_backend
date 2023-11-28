package com.bitsbids.bitsbids;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController{
    @GetMapping(path = "/hello") // tells spring that this is an endpoint 
    public String helloWorld(){
        return "Hello World!";
    }
}