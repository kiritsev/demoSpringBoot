package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MainController {

    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name) {
        if(name == null) {
            name = "anonim";
        }
        return "Hello, <b> -> [" + name + "] </b>";
    }

}

