package com.salex.actuatordemo.controller;

import com.salex.actuatordemo.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private FooService fooService;

    @PostMapping("/increment")
    public Long increment() {
        return fooService.increment();
    }

}
