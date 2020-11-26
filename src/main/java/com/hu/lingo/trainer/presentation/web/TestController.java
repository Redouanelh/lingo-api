package com.hu.lingo.trainer.presentation.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tests")
public class TestController {

    @GetMapping("/test")
    public String getTestMethod() {
        return "getTestMethod() works!";
    }
}
